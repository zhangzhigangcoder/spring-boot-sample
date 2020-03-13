package org.spring.boot.strategy.keygen;

import java.util.Calendar;
import java.util.Properties;

import com.google.common.base.Preconditions;

/**
 * Snowflake distributed primary key generator.
 * 
 * <p>
 * 0 - 00000000 00000000 00000000 00000000 00000000 0 - 00000000 00 - 00000000 0000
 * Use snowflake algorithm. Length is 64 bit.
 * </p>
 * 
 * <pre>
 * 1bit sign bit.
 * 41bits timestamp offset from 2016.11.01(ShardingSphere distributed primary key published data) to now(2 ^ 41 /365/24/60/60/1000=69.7年)
 * 10bits worker process id(1024)
 * 12bits auto increment offset in one mills(4096个)
 * </pre>
 * 
 * <p>
 * Call @{@code SnowflakeShardingKeyGenerator.setWorkerId} to set worker id, default value is 0.
 * </p>
 * 
 * <p>
 * Call @{@code SnowflakeShardingKeyGenerator.setMaxTolerateTimeDifferenceMilliseconds} to set max tolerate time difference milliseconds, default value is 0.
 * </p>
 * 
 */
public final class SnowflakeKeyGenerator implements KeyGenerator {
    
	// 起使时间的毫秒数
    public static final long EPOCH;
    
    // 自增序列的bit位数
    private static final long SEQUENCE_BITS = 12L;
    
    // 工作机器ID的bit位数
    private static final long WORKER_ID_BITS = 10L;
    
    // 自增序列的掩码：4095，防止溢出
    // 11111111 1111
    private static final long SEQUENCE_MASK = (1 << SEQUENCE_BITS) - 1;
    
    // 工作机器ID左移bit位数：自增序列的位数
    private static final long WORKER_ID_LEFT_SHIFT_BITS = SEQUENCE_BITS;
    
    // 时间差左移bit位数： 工作机器ID左移bit位数(12) + 工作机器ID的bit位数(10)
    private static final long TIMESTAMP_LEFT_SHIFT_BITS = WORKER_ID_LEFT_SHIFT_BITS + WORKER_ID_BITS;
    
    // 工作机器ID最大值： 1 * 2^10 = 1024
    private static final long WORKER_ID_MAX_VALUE = 1L << WORKER_ID_BITS;
    
    // 工作机器ID默认值0
    private static final long WORKER_ID = 0;
    
    // 最大容忍时间差毫秒数 默认10ms
    private static final int MAX_TOLERATE_TIME_DIFFERENCE_MILLISECONDS = 10;
    
    private Properties properties = new Properties();
    
    private byte sequenceOffset;
    
    private long sequence;
    
    private long lastMilliseconds;
    
    static {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2016, Calendar.NOVEMBER, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        EPOCH = calendar.getTimeInMillis();
    }
    
    @Override
    public String getType() {
        return "SNOWFLAKE";
    }
    
    @Override
    public synchronized Comparable<?> generateKey() throws InterruptedException {
    	// 当前系统时间毫秒数
        long currentMilliseconds = getCurrentMillis();
        // 判断是否需要等待容忍时间差，如果需要，则等待时间差过去，然后再获取当前系统时间
        if (waitTolerateTimeDifferenceIfNeed(currentMilliseconds)) {
            currentMilliseconds = getCurrentMillis();
        }
        // 如果最后一次毫秒与当前系统时间毫秒相同，即还在同一毫秒内
        if (lastMilliseconds == currentMilliseconds) {
        	// SEQUENCE_MASK最大值为4095，支持每毫秒并发4096个
        	// 防止sequence位溢出(12)
        	// SEQUENCE_MASK = 11111111 1111
        	// sequence + 1 = 1111111 1111(4095) + 1 = 1 000000000 0000
        	//   11111111 1111
        	// 1 00000000 0000
        	// 0 00000000 0000 = 0
            if (0L == (sequence = (sequence + 1) & SEQUENCE_MASK)) {
                currentMilliseconds = waitUntilNextTime(currentMilliseconds);
            }
        } else {
        	// 上一毫秒已经过去，把序列值重置为1或0
            vibrateSequenceOffset();
            sequence = sequenceOffset;
        }
        lastMilliseconds = currentMilliseconds;
        return ((currentMilliseconds - EPOCH) << TIMESTAMP_LEFT_SHIFT_BITS) | (getWorkerId() << WORKER_ID_LEFT_SHIFT_BITS) | sequence;
    }
    
    private boolean waitTolerateTimeDifferenceIfNeed(final long currentMilliseconds) throws InterruptedException {
        if (lastMilliseconds <= currentMilliseconds) {
            return false;
        }
        long timeDifferenceMilliseconds = lastMilliseconds - currentMilliseconds;
        Preconditions.checkState(timeDifferenceMilliseconds < getMaxTolerateTimeDifferenceMilliseconds(), 
                "Clock is moving backwards, last time is %d milliseconds, current time is %d milliseconds", lastMilliseconds, currentMilliseconds);
        Thread.sleep(timeDifferenceMilliseconds);
        return true;
    }
    
    private long getWorkerId() {
        long result = Long.valueOf(properties.getProperty("worker.id", String.valueOf(WORKER_ID)));
        Preconditions.checkArgument(result >= 0L && result < WORKER_ID_MAX_VALUE);
        return result;
    }
    
    private int getMaxTolerateTimeDifferenceMilliseconds() {
        return Integer.valueOf(properties.getProperty("max.tolerate.time.difference.milliseconds", String.valueOf(MAX_TOLERATE_TIME_DIFFERENCE_MILLISECONDS)));
    }
    
    private long waitUntilNextTime(final long lastTime) {
        long result = getCurrentMillis();
        while (result <= lastTime) {
            result = getCurrentMillis();
        }
        return result;
    }
    
    /**
     * 把序列值重置为1或0
     * byte是8位二进制
     * sequenceOffset默认值是0000 0000
     * ~sequenceOffset取反运算后是1111 1111
     * &1(0000 00001) 位与运算后是0000 0001，转换为十进制就是1
     * 
     * 1111 1111
     * 0000 0001
     * 0000 0001 = 1
     * 
     * 1111 1110
     * 0000 0001
     * 0000 0000 = 0
     */
    private void vibrateSequenceOffset() {
        sequenceOffset = (byte) (~sequenceOffset & 1);
    }

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}
	
	 private long getCurrentMillis() {
		 return System.currentTimeMillis();
	 }
    
}

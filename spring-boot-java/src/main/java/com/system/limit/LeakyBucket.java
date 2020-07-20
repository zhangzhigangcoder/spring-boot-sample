package com.system.limit;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 限流-漏桶算法
 *
 */
public class LeakyBucket {
	
	/**
	 * 水流出速率 
	 * 每秒处理请求数 = 1000 * rate
	 */
	private double rate;
	
	/**
	 * 桶大小
	 */
	private long burst;
	
	/**
	 * 最后更新时间
	 */
	private long refreshTime;
	
	/**
	 * 桶里面的水量
	 */
	private long water;
	
	public LeakyBucket(double rate, long burst) {
		this.rate = rate;
		this.burst = burst;
	}
	
	/**
	 * 刷新桶的水量
	 */
	private void refreshWater() {
		long now = System.currentTimeMillis();
		// 若并发量比较大时，桶里的水会越来越多
		// 若并发量降低，桶里的水会越来越少
		this.water = (long) Math.max(0, this.water - (now - refreshTime) * rate);
		this.refreshTime = now;
	}
	
	/**
	 * 判断漏桶是否可以加水
	 * @return
	 */
	public synchronized boolean tryAcquire() {
		refreshWater();
		if (this.water < this.burst) {
			water++;
			return true;
		}
		return false;
	}

	public static void main(String[] args) throws Exception {
		LeakyBucket leakyBucket = new LeakyBucket(1, 100);
		ExecutorService executorService = Executors.newCachedThreadPool();
		for (int i = 0; i < 120; i++) {
			executorService.execute(() -> {
				System.out.println(leakyBucket.tryAcquire());
			});
		}
		executorService.shutdown();
	}
}
package org.spring.boot.constant;

public interface RedisConstant {
	
	/**
	 * 自定义channel
	 */
	String CHANNEL = "test_channel";
	
	/**
	 * redis默认channel,监听database(5)中所有key删除事件
	 */
	String KEY_EVENT_DEL = "__keyevent@5__:del";
	
	/**
	 * redis默认channel,监听database(5)中所有key过期事件
	 */
	String KEY_EVENT_EXPIRED = "__keyevent@5__:expired";
	
}
package org.spring.boot.service;

import org.spring.boot.cache.CacheService;
import org.spring.boot.constant.RedisConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RedisService {
	
	@Autowired
	private CacheService cacheService;
	
	public void sendChannelMsg(String msg) {
		cacheService.sendChannelMsg(RedisConstant.CHANNEL, msg);
	}
	
	public void receiveMsg(String msg) {
		System.out.println("监听到消息: " + msg);
	}
	
}
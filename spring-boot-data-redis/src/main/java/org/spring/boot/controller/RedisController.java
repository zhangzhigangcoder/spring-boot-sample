package org.spring.boot.controller;

import org.spring.boot.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {
	
	@Autowired
	private RedisService redisService;
	
	@GetMapping("/sendMsg")
	public String getSessionId(String msg) {
		redisService.sendChannelMsg(msg);
		return "success";
	}
	
}

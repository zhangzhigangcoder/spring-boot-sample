package org.spring.boot.controller;

import javax.annotation.Resource;

import org.spring.boot.entity.City;
import org.spring.boot.entity.User;
import org.spring.boot.service.ICityService;
import org.spring.boot.service.IUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {
	
	@Resource
	private ICityService cityService;
	
	@Resource
	private IUserService userService;
	
	@GetMapping("/api/city")
	public City findOneCity(@RequestParam(value="cityName",required=true) String cityName) {
		return cityService.findCityByName(cityName);
	}
	
	
	@GetMapping("/api/user")
	public User findByName(@RequestParam(value="userName",required=true) String userName) {
		return userService.findByName(userName);
	}
	

}

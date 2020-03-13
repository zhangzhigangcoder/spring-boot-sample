package org.spring.boot.controller;

import javax.annotation.Resource;

import org.spring.boot.entity.City;
import org.spring.boot.service.ICityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CityRestController {
	
	@Resource
	private ICityService cityService;
	
	@GetMapping("/api/city")
	public City findOneCity(@RequestParam(value="cityName",required=true) String cityName) {
		return cityService.findCityByName(cityName);
	}

}

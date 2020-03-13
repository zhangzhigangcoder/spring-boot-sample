package org.spring.boot.controller;

import javax.annotation.Resource;

import org.spring.boot.entity.City;
import org.spring.boot.service.ICityService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CityRestController {

	@Resource
	private ICityService cityService;

	@GetMapping("/api/city")
	public City findOneCity(@RequestParam(value = "cityName", required = true) String cityName) {
		return cityService.findCityByName(cityName);
	}

	@GetMapping(value = "/api/city/{id}")
	public City findOneCity(@PathVariable("id") Long id) {
		return cityService.findCityById(id);
	}

	@PostMapping(value = "/api/city")
	public void createCity(@RequestBody City city) {
		cityService.saveCity(city);
	}

	@PutMapping(value = "/api/city")
	public void modifyCity(@RequestBody City city) {
		cityService.updateCity(city);
	}

	@DeleteMapping(value = "/api/city/{id}")
	public void modifyCity(@PathVariable("id") Long id) {
		cityService.deleteCity(id);
	}

}

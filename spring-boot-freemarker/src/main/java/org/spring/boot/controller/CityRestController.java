package org.spring.boot.controller;

import javax.annotation.Resource;

import org.spring.boot.service.ICityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CityRestController {
	
	@Resource
	private ICityService cityService;
	
	@GetMapping("/api/city/{id}")
	public String findOneCity(Model model, @PathVariable("id") Long id) {
		model.addAttribute("city",cityService.findCityById(id));
		return "city";
	}
	
	@GetMapping("/api/cities")
	public String cities(Model model) {
		model.addAttribute("cityList",cityService.findAllCity());
		return "cityList";
	}

}

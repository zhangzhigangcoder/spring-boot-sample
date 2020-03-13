package org.spring.boot;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.spring.boot.entity.City;
import org.spring.boot.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CityTests {
	
	@Autowired
	private ICityService cityService;
	
	@Before
	public void setUp() {}
	
	@Test
	public void test() {
		City city = cityService.findCityByName("苏州");
		System.out.println(city);
	}
	
}




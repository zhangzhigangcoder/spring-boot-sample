package org.spring.boot;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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
	public void setUp() {
//		cityService.deleteAllCities();
	}
	
	@Test
	public void test() {
		cityService.create(22L,"北京");
//		cityService.create(2L,"连云港");
//		cityService.create(3L,"苏州");
//		cityService.create(4L,"淮安");
//		cityService.create(5L,"扬州");
//
//		Assert.assertEquals(5, cityService.getAllCities().intValue());
//		
//		cityService.deleteByName("苏州");
//		cityService.deleteByName("淮安");
//		
//		Assert.assertEquals(3, cityService.getAllCities().intValue());

		
	}
	
}




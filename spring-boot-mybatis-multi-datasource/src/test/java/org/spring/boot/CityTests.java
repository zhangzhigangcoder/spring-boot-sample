package org.spring.boot;

import java.util.Random;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.spring.boot.entity.City;
import org.spring.boot.entity.User;
import org.spring.boot.service.ICityService;
import org.spring.boot.service.IMultiDatasourceTransationService;
import org.spring.boot.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CityTests {
	
	@Autowired
	private ICityService cityService;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IMultiDatasourceTransationService multiDatasourceTransationService;
	
	@Before
	public void setUp() {
//		cityService.deleteAllCities();
	}
	
	@Test
	public void test() {
		City city = cityService.findCityByName("温岭市");
		System.out.println(city);
	}
	
	@Test
	public void test3() {
		cityService.addCity(111, "苏州");
	}
	
	@Test
	public void test2() {
		User user = userService.findByName("泥瓦匠");
		System.out.println(user);
	}
	
	@Test
	public void test4() {
		userService.addUser(new Random().nextInt(1000000000), "zhangsan");
	}
	
	@Test
	public void test5() {
		multiDatasourceTransationService.multiDatasourceRollback();
	}
	
}




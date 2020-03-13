package org.spring.boot;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.spring.boot.entity.City;
import org.spring.boot.repository.ICityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CityTests {

	@Autowired
	private ICityRepository cityRepository;

	@Test
	public void test() {
		
		cityRepository.save(new City("宿迁", 1));
		cityRepository.save(new City("连云港", 1));
		cityRepository.save(new City("苏州", 1));
		cityRepository.save(new City("淮安", 1));
		cityRepository.save(new City("扬州", 1));

		Assert.assertEquals(5, cityRepository.findAll().size());

		Assert.assertEquals("宿迁", cityRepository.findByCityName("宿迁").getCityName());

		Assert.assertEquals("苏州", cityRepository.findCity("苏州").getCityName());

		Assert.assertEquals("苏州", cityRepository.findByCityNameAndProvinceId("苏州", 1).getCityName());

		cityRepository.delete(cityRepository.findByCityName("苏州"));

		Assert.assertEquals(4, cityRepository.findAll().size());

	}

}

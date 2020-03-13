package org.spring.boot;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.spring.boot.entity.primary.City;
import org.spring.boot.entity.secondary.User;
import org.spring.boot.repository.primary.ICityRepository;
import org.spring.boot.repository.secondary.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Autowired
	private ICityRepository cityRepository;
	
	@Resource
	private IUserRepository userRepository;
	

	@Test
	public void test() {
		
		cityRepository.save(new City("宿迁", 1));
		cityRepository.save(new City("连云港", 1));
		cityRepository.save(new City("苏州", 1));
		cityRepository.save(new City("淮安", 1));
		cityRepository.save(new City("扬州", 1));

		Assert.assertEquals(5, cityRepository.findAll().size());

		userRepository.save(new User("zhangsan"));
		userRepository.save(new User("lisi"));
		userRepository.save(new User("wangwu"));

		Assert.assertEquals(3, userRepository.findAll().size());

	}

}

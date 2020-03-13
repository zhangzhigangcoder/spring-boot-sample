package org.spring.boot;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
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
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	@Before
	public void setUp() {}
	
	/**
	 * 测试二级缓存
	 */
	@Test
	public void test() {
		long first = System.currentTimeMillis();
		List<City> cities = cityService.listAllCities();
		cities.forEach(p -> System.out.println(p));
		System.out.println(System.currentTimeMillis() - first);
//		
//		cityService.insertCity();
		
//		first = System.currentTimeMillis();
//		List<City> cities2 = cityService.listAllCities();
//		cities2.forEach(p -> System.out.println(p));
//		System.out.println(System.currentTimeMillis()-first);
	}
	
	/**
	 * 测试一级缓存
	 */
	@Test
	public void test1() {
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		long first = System.currentTimeMillis();
		List<City> cities =  sqlSession.selectList("org.spring.boot.dao.ICityDao.findByName", "苏州");
		cities.forEach(p -> System.out.println(p));
		System.out.println(System.currentTimeMillis()-first);
		
		// insert
		City city = new City();
		city.setCityName("suzhou2");
		sqlSession.insert("org.spring.boot.dao.ICityDao.insertCity", city);
		
//		sqlSession = sqlSessionFactory.openSession();
		first = System.currentTimeMillis();
		List<City> cities2 =  sqlSession.selectList("org.spring.boot.dao.ICityDao.findByName", "苏州");
		cities2.forEach(p -> System.out.println(p));
		System.out.println(System.currentTimeMillis()-first);
	}
	
	@Test
	public void test2() {
		City city = cityService.findCityByName("宿迁");
		System.out.println(city);
	}
	
	@Test
	public void testInsert() {
		City city = new City();
		city.setCityName("suzhou2");
		cityService.insertCity(city);
		System.out.println(city);
	}
}




package org.spring.boot.service;

import java.util.List;

import org.spring.boot.entity.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 城市业务逻辑实现类
 * 
 * @author zhangzhigang
 */
@Service
public class CityServiceImpl implements ICityService{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<City> findCityByName(String cityName) {
		return jdbcTemplate.queryForList("select * from city where city_name = ?",City.class, cityName);
	}

	/**
	 * 声明式事务,对Exception进行回滚
	 */
	@Transactional(rollbackFor=Exception.class)
	@Override
	public void create(Long id,String cityName) {
		jdbcTemplate.update("insert into city(id,province_id,city_name) values(?,1,?)", id,cityName);
		// 在这里故意抛出一个异常，迫使事务回滚
//		throw new IllegalArgumentException();
	}

	@Override
	public void deleteByName(String cityName) {
		jdbcTemplate.update("delete from city where city_name = ?", cityName);
	}

	@Override
	public Integer getAllCities() {
        return jdbcTemplate.queryForObject("select count(1) from city", Integer.class);
	}

	@Override
	public void deleteAllCities() {
        jdbcTemplate.update("delete from city");
	}

}

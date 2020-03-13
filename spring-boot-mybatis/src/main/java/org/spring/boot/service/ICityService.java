package org.spring.boot.service;

import java.util.List;

import org.spring.boot.entity.City;

/**
 * 城市业务逻辑接口类
 * 
 * @author zhangzhigang
 */
public interface ICityService {

	/**
	 * 根据城市名称，查询城市信息
	 * @param cityName
	 * @return
	 */
	City findCityByName(String cityName);
	
	/**
	 * 查询所有城市
	 * @return
	 */
	List<City> listAllCities();
	
	void insertCity(City city);
}

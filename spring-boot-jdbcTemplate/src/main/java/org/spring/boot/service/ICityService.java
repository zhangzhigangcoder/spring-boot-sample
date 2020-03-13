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
	List<City> findCityByName(String cityName);
	
	/**
	 * 新增一个城市
	 * @param cityName
	 */
	void create(Long id,String cityName);
	
	/**
	 * 根据cityName删除一个城市
	 * @param cityName
	 */
	void deleteByName(String cityName);
	
	/**
	 * 获取城市数量
	 * @return
	 */
	Integer getAllCities();
	
	/**
	 * 删除所有城市
	 */
	void deleteAllCities();
	

}

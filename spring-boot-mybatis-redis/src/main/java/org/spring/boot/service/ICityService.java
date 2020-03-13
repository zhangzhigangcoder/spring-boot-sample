package org.spring.boot.service;

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
	 * 获取城市逻辑：
	 * 	如果缓存存在，从缓存中获取城市信息
	 *  如果缓存不存在，从DB中获取，然后插入缓存
	 * @param id
	 * @return
	 */
	City findCityById(Long id);
	
	Long saveCity(City city);
	
	/**
	 * 更新城市逻辑：
	 *  更新DB
	 * 	如果缓存存在，删除
	 *  如果缓存不存在，不操作
	 * @param city
	 * @return
	 */
	Long updateCity(City city);
	
	/**
	 * 删除城市逻辑：
	 * 	删除DB
	 *  如果缓存存在，删除
	 * @param id
	 * @return
	 */
	Long deleteCity(Long id);
	
	
}

package org.spring.boot.dao;

import org.apache.ibatis.annotations.Param;
import org.spring.boot.entity.City;

/**
 * 城市DAO接口类
 * 
 * @author zhangzhigang
 */
public interface ICityDao {

	
	City findById(Long id);
	
	/**
	 * 根据城市名称，查询城市信息
	 * @param cityName 城市名
	 */
	City findByName(@Param("cityName") String cityName);
	
	
	Long saveCity(City city);
	
	Long updateCity(City city);
	
	Long deleteCity(Long id);
}

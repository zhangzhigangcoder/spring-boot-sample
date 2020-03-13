package org.spring.boot.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.spring.boot.entity.City;

/**
 * 城市DAO接口类
 * 
 * @author zhangzhigang
 */
public interface ICityDao {

	/**
	 * 根据城市名称，查询城市信息
	 * @param cityName 城市名
	 */
	City findByName(@Param("cityName") String cityName);
	
	/**
	 * 列出所有城市
	 * @return
	 */
	List<City> listAllCities();
	
	/**
	 * 新增一个城市
	 */
	void insertCity(City city);
	
}

package org.spring.boot.dao.cluster;

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
	
	void addCity(@Param("id") Integer id, @Param("cityName") String cityName);
	
}

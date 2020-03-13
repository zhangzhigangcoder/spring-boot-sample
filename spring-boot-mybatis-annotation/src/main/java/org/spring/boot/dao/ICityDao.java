package org.spring.boot.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.spring.boot.entity.City;

/**
 * 城市DAO接口类
 * 
 * @author zhangzhigang
 */
/*
 * 配置为Mybatis的Mapper,会全路径扫描，不用配置@MapperScan注解
 */
@Mapper  
public interface ICityDao {

	/**
	 * 根据城市名称，查询城市信息
	 * @param cityName 城市名
	 */
	@Select("SELECT * FROM city where city_name = #{cityName}")
	@Results({
		@Result(property = "id", column = "id"),
		@Result(property = "provinceId", column = "province_id"),
		@Result(property = "cityName", column = "city_name"),
		@Result(property = "description", column = "description")
	})
	City findByName(@Param("cityName") String cityName);
	
}

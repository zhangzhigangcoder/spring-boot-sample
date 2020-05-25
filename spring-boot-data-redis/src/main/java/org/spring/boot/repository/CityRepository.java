package org.spring.boot.repository;

import org.spring.boot.entity.City;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

/**
 * 测试Spring缓存机制
 * @author zhangzhigang
 *
 */
@Repository
public class CityRepository {

	/**
	 * 设置当前方法应用缓存
	 * 方法只执行一次，其它的从缓存中读取
	 * @param id
	 * @return
	 */
	@Cacheable(cacheNames={"users"}, key="#id", condition="#id > 0")
	public City findCity(String id) {
		return new City(id, "苏州");
	}
	
	/**
	 * 设置当前方法更新缓存
	 * 方法每次都会执行，并且更新缓存
	 * @param id
	 * @return
	 */
	@CachePut(cacheNames="users",key="#id")
	public City updateCity(String id) {
		return null;
	}
	
	/**
	 * 清除所有缓存
	 * @param id
	 */
	@CacheEvict(cacheNames="users",allEntries=true)
	public void loadCity(String id) {
		
	}
	
}

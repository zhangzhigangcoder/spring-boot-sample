package org.spring.boot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.boot.dao.ICityDao;
import org.spring.boot.entity.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

/**
 * 城市业务逻辑实现类
 * 
 * @author zhangzhigang
 */
@Service
public class CityServiceImpl implements ICityService{

	private static final Logger log = LoggerFactory.getLogger(CityServiceImpl.class);
	
	@Autowired
	private ICityDao cityDao;
	
	/**
	 * 默认只能使用RedisTemplate,不能声明泛型参数，可以存储Object,如果声明泛型，必须自己实现RedisSerializer<T>接口
	 * StringRedisTemplate 只支持String类型存储
	 */
	@Autowired
	private RedisTemplate<String,City> redisTemplate;
	
	@Override
	public City findCityByName(String cityName) {
		return cityDao.findByName(cityName);
	}

	@Override
	public City findCityById(Long id) {
		String key = "city_" + id;
		ValueOperations<String, City> operations = redisTemplate.opsForValue();
		// 缓存存在
		boolean hasKey = redisTemplate.hasKey(key);
		if (hasKey) {
			City city = operations.get(key);
			log.info("CityServiceImpl.findCityById() : 从缓存中获取城市 >> " + city.toString());
			return city;
		}
		// 从DB中获取城市信息
		City city = cityDao.findById(id);
		
		//插入缓存
		//用默认的RedisTemplate，插入的key为"\xac\xed\x00\x05t\x00\x06city_1"
		operations.set(key, city,10000,TimeUnit.SECONDS);
		log.info("CityServiceImpl.findCityById() : 城市插入缓存 >> " + city.toString());
		
		return city;
	}

	@Override
	public Long saveCity(City city) {
		return cityDao.saveCity(city);
	}

	@Override
	public Long updateCity(City city) {
		Long ret = cityDao.updateCity(city);
		
		//缓存存在，删除缓存
		String key = "city_" + city.getId();
		boolean hasKey = redisTemplate.hasKey(key);
		if (hasKey) {
			redisTemplate.delete(key);
			log.info("CityServiceImpl.updateCity() : 从缓存中删除城市 >> " + city.toString());

		}
		return ret;
	}

	@Override
	public Long deleteCity(Long id) {
		Long ret = cityDao.deleteCity(id);
		
		//缓存存在，删除缓存
		String key = "city_" + id;
		boolean hasKey = redisTemplate.hasKey(key);
		if (hasKey) {
			redisTemplate.delete(key);
            log.info("CityServiceImpl.deleteCity() : 从缓存中删除城市 ID >> " + id);
		}
		return ret;
	}

}

package org.spring.boot.service;

import java.util.List;

import javax.annotation.Resource;

import org.spring.boot.dao.ICityDao;
import org.spring.boot.entity.City;
import org.springframework.stereotype.Service;

/**
 * 城市业务逻辑实现类
 * 
 * @author zhangzhigang
 */
@Service
public class CityServiceImpl implements ICityService{

	@Resource
	private ICityDao cityDao;
	
	@Override
	public City findCityByName(String cityName) {
		return cityDao.findByName(cityName);
	}

	@Override
	public City findCityById(Long id) {
		return cityDao.findById(id);
	}

	@Override
	public List<City> findAllCity() {
		return cityDao.findAllCity();
	}

}

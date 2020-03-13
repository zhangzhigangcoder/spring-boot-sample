package org.spring.boot.service;

import javax.annotation.Resource;

import org.spring.boot.dao.cluster.ICityDao;
import org.spring.boot.entity.City;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 城市业务逻辑实现类
 * 
 * @author zhangzhigang
 */
@Service
public class CityServiceImpl implements ICityService{

	@Resource
	private ICityDao cityDao;
	
	@Transactional
	@Override
	public City findCityByName(String cityName) {
		return cityDao.findByName(cityName);
	}

	@Transactional(value="clusterTransactionManager", rollbackFor=Exception.class, propagation=Propagation.REQUIRES_NEW)
	@Override
	public void addCity(Integer id, String cityName) {
		cityDao.addCity(id, cityName);
		throw new IllegalArgumentException();
	}

}

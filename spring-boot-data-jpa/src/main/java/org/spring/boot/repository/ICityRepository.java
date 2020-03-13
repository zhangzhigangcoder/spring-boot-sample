package org.spring.boot.repository;

import org.spring.boot.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ICityRepository  extends JpaRepository<City, Long>{

	City findByCityName(String cityName);
	
	City findByCityNameAndProvinceId(String cityName,Integer provinceId);
	
	@Query("from City c where c.cityName=:cityName")
	City findCity(@Param("cityName") String cityName);
}

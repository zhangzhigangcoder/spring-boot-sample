package org.spring.boot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 城市实体类
 * 
 * @author zhangzhigang
 */
@Entity
public class City {

    /**
     * 城市编号
     */
	@Id
	@GeneratedValue
    private Long id;

    /**
     * 省份编号
     */
	@Column(name="province_id")
    private Integer provinceId;

    /**
     * 城市名称
     */
	@Column(name="city_name")
    private String cityName;

    /**
     * 描述
     */
	@Column(name="description")
    private String description;
	
	
	public City() {}
	
	public City(String cityName,Integer provinceId) {
		this.cityName = cityName;
		this.provinceId = provinceId;
	}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

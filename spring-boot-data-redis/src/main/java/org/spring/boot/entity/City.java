package org.spring.boot.entity;

import java.io.Serializable;

/**
 * 城市实体类
 * 
 * @author zhangzhigang
 */
public class City implements Serializable {

	private static final long serialVersionUID = -5191786080892330067L;

	/**
     * 省份编号
     */
    private String id;

    /**
     * 城市名称
     */
    private String cityName;
    
    public City() {
	}
    
    public City(String id, String cityName) {
    	this.id = id;
    	this.cityName = cityName;
	}

	public String getId() {
		return id;
	}

	public String getCityName() {
		return cityName;
	}

	@Override
	public String toString() {
		return "City [id=" + id + ", cityName=" + cityName + "]";
	}
	
}
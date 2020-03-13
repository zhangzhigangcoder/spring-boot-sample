package org.spring.boot.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 城市实体类
 * 
 * @author zhangzhigang
 */
public class City implements Serializable {

	private static final long serialVersionUID = 6001085602440786108L;

	/**
     * 城市编号
     */
    private String id;

    /**
     * 城市名称
     */
    private String cityName;

    /**
     * 描述
     */
    private Date create;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

	public Date getCreate() {
		return create;
	}

	public void setCreate(Date create) {
		this.create = create;
	}

	@Override
	public String toString() {
		return "City [id=" + id + ", cityName=" + cityName + ", create=" + create + "]";
	}
	
}

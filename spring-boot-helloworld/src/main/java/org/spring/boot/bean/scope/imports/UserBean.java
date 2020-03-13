package org.spring.boot.bean.scope.imports;

import org.spring.boot.properties.UserProperties;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 用来注入的类
 */
public class UserBean {
	
	private String name;
	
	@Autowired
	private UserProperties userProperties;
	
	public UserBean(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}

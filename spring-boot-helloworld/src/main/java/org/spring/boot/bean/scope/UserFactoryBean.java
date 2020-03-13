package org.spring.boot.bean.scope;

/**
 * 测试FactoryBean接口向spring容器注入Bean
 *
 */
public class UserFactoryBean {
	
	private String name;
	
	public UserFactoryBean() {}
	
	public UserFactoryBean(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

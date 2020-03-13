package org.spring.boot.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 框架配置
 * @ConfigurationProperties解析类:ConfigurationPropertiesBindingPostProcessor
 * 通过@EnableConfigurationProperties注解进行注入该解析类
 * @author zhangzhigang
 */
@ConfigurationProperties(prefix = UserProperties.USER_PREFIX)
public class UserProperties {
	
	static final String USER_PREFIX = "boot.user";

	private String name;
	
	private int age;

	public UserProperties() {
		System.out.println("-----------UserProperties------------");
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	
}




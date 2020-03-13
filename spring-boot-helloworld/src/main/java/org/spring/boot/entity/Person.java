package org.spring.boot.entity;

import org.spring.boot.properties.UserProperties;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 测试@Configurable
 * @author qw
 *
 */
public class Person {
	
//	@Autowired
//	private UserProperties userProperties;
	
	private Long id;
	
	private String name;
	
	private int age;
	
	public Person() {
	}
	
	public Person(Long id, String name, int age) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
//		userProperties.getName();
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

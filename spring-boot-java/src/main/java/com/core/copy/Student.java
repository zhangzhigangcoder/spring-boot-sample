package com.core.copy;

public class Student implements Cloneable {
	
	private String name;
	
	private int age;

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String print() {
		return "Student [name=" + name + ", age=" + age + "]";
	}

	public Student(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	@Override
	protected Object clone() {
		try {
			return super.clone();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

package com.core.copy;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Person implements Cloneable {
	
	private String name;
	
	private int age;

	private String birthDate;
	
	private Student s;

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
		return "Person [name=" + name + ", age=" + age + "]";
	}

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	public Student getS() {
		return s;
	}

	public void setS(Student s) {
		this.s = s;
	}

	@Override
	protected Object clone() {
		Person per = null;
		try {
			per = (Person) super.clone();
		} catch (Exception e) {
			e.printStackTrace();
		}
		per.s = (Student) s.clone(); //深拷贝：就是对引用的对象单独拷贝一次哦。
		return per;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((s == null) ? 0 : s.hashCode());
		// 116434388
		return 116434388;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public static void main(String[] args) {
		Person p1 = new Person("zzg", 1);
		Person p2 = new Person("zzg", 0);
//		System.out.println(p1.hashCode());
		
		Map<Person, String> map = new HashMap<>();
		map.put(p1, "zzg1");
		System.out.println(map.get(p2));
	}
	
}

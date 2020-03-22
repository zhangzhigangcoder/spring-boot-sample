package com.core.copy;

public class Test {
	
	public static void main(String[] args) {
		String s = new String("hahaha");
		String s2 = new String("hahaha");
		String s3 = "haha";
		String s4 = "haha2";
		System.out.println(s == s2);
		System.out.println(s3 == s4);
	}
	
	public static void test1() {
		Person p = new Person("zhangsan", 11);
		// 引用复制 浅复制
		Person p2 = p;
		System.out.println(p);
		System.out.println(p2);
	}
	
	public static void cloneTest() {
		Person p = new Person("zhangsan", 11);
		Person p2 = (Person) p.clone();
		System.out.println(p + "-" + p.print());
		System.out.println(p2 + "-" + p2.print());
		p2.setAge(20);
		p2.setName("lisi");
		System.out.println(p.print());
	}
	
	public static void clone2Test() {
		Student s = new Student("s_name1", 11);
		Person p = new Person("zhangsan", 22);
		p.setS(s);
		// Student类重写clone方法深拷贝，默认浅拷贝
		Person p2 = (Person) p.clone();
		System.out.println(p.getS() + "-" + p.getS().print());
		System.out.println(p2.getS() + "-" + p2.getS().print());
		p2.getS().setAge(20);
		p2.getS().setName("lisi");
		System.out.println(p2.getS().print());
	}

}

package com.core.thread.string;

public class StringTest {

	public static void main(String[] args) {
		test2();
	}
	
	public static void test1() {
		String s1 = "abc";
		String s2 = "abc";
		String s3 = "ab" + "c";
		System.out.println(s1 == s2);
		System.out.println(s1 == s3);
	}
	
	public static void test2() {
		String s1 = "abc";
		String s2 = "ab" + new String("c");
		String s3 = new String("abc");
		String s4 = new String("abc");
		System.out.println(s1 == s2);
		System.out.println(s1 == s3);
		System.out.println(s3 == s2);
		System.out.println(s3 == s4);
	}
	
	public static void test3() {
		String s0 = "abc";
		String s1 = new String("abc");
		String s2 = new String("abc");
		System.out.println(s0 == s1); // false 
		s1.intern();
		// 查找常量池中是否有相同Unicode的字符串常量，如果有，则返回其的引用，
		// 如果没有，则在常量池中增加一个Unicode等于str的字符串并返回它的引用
		s2 = s2.intern();// 把常量池中的"abc"的引用赋给s2
		System.out.println(s0 == s1);// false 虽然执行了s1.intern(),但它的返回值没有赋值给s1
		System.out.println(s0 == s1.intern()); // true 说明s1.intern()返回的是常量池中"abc"的引用
		System.out.println(s0 == s2);
	}
	
	
}

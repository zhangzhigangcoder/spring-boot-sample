package com.core.jdk8.interfacee;

/**
 * 演示 Interface中添加默认方法，为了弥补类不能多继承问题
 * @author zhangzhigang
 * @see DefaultSample
 *
 */
public interface Interface1 {
	
	void method1(String str);
	
	default void log(String str) {
		System.out.println("I2 logging: " + str);
	}
	
	default void log2(String str) {
		System.out.println("I2 logging: " + str);
	}
	
	/**
	 * 实现类不用重写
	 * @param str
	 */
	static void print(String str) {
		System.out.println("printing " + str);
	}
	
}

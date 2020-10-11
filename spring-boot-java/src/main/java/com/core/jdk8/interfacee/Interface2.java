package com.core.jdk8.interfacee;

/**
 * 演示 Interface中添加默认方法
 * @author zhangzhigang
 * @see DefaultSample
 */
public interface Interface2 {
	
	void method2(String str);
	
	default void log(String str) {
		System.out.println("I2 logging: " + str);
	}
	
}

package com.core.jdk8.interfacee;

/**
 * 演示 Interface中添加默认方法
 * @author zhangzhigang
 * @see Interface1 Interface2
 *
 */
public class DefaultSample  implements Interface1, Interface2{

	@Override
	public void method1(String str) {
	}

	@Override
	public void method2(String str) {
	}

	/**
	 * 当多个接口中同时定义了该方法时，实现类要重写
	 */
	@Override
	public void log(String str) {
		System.out.println("MyClass logging: " + str);
		Interface1.print("abc");
	}
	
}

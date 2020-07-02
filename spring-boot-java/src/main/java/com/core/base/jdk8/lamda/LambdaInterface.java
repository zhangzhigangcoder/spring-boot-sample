package com.core.base.jdk8.lamda;

/**
 * 演示 Lambda 接口定义
 * Lambda接口只能有一个抽象方法，@FunctionalInterface可省略
 * @author zhangzhigang
 * @see LambdaSample
 *
 */
@FunctionalInterface
public interface LambdaInterface {

	void method1(String str);
	
	default void m2() {}
	
	static void m3() {}
	
}

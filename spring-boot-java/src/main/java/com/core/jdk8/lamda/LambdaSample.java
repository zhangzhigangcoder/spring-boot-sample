package com.core.jdk8.lamda;

/**
 * 演示 Lambda使用，简化了实现类的创建
 * @author zhangzhigang
 * @see LambdaInterface
 *
 */
public class LambdaSample {

	public static void main(String[] args) {
		LambdaInterface i1 = (s) -> System.out.println(s);
		i1.method1("abc");
	}
}

package com.effective.item19;

/**
 * 设计用于子类继承的父类
 * 测试:父类构造方法中不允许调用可以子类重写的方法
 * @author zhangzhigang
 */
public class Super {

	public Super() {
		overrideMe();
	}
	
	public void overrideMe() {}
	
	public void say() {
		System.out.println(11);
	}
}

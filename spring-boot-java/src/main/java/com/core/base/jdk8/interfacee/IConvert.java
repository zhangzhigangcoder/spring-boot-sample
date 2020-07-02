package com.core.base.jdk8.interfacee;

/**
 *	在java8中，满足下面任意一个条件的接口都是函数式接口：
 *
 * 1、被@FunctionalInterface注释的接口，满足@FunctionalInterface注释的约束。
 *
 * 2、没有被@FunctionalInterface注释的接口，但是满足@FunctionalInterface注释的约束
 * 
 *		a、接口有且只能有个一个抽象方法，只有方法定义，没有方法体
 *  	b、在接口中可以重写Object类中的public方法，但不算是函数式接口的方法。
 */
@FunctionalInterface
public interface IConvert<F,T> {

	T convert(F form);
   
}

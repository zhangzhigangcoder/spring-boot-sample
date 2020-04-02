package com.core.jdk8.grammar;

import java.util.function.Function;

public class Test {

	public static void main(String[] args) {
		
//		// 1、lambda表达式
//		IConvert<String, String> convert0 = p -> p + "s";
//		System.out.println(convert0.convert("123"));
//		
//		// 2、方法引用
//		// static methods
//		// 相当于为IConvert创建了一个实现类，重写了convert方法，方法实现是Something类的startsWith方法
//		IConvert<String, String> convert = Something::startsWith;
//		System.out.println(convert.convert("123"));
//				
//		// object methods
//		Something something = new Something();
//		IConvert<String, String> converter2 = something::endWith;
//		System.out.println(converter2.convert("Java"));
//		
//		// 3、构造方法引用
//		// constructor methods
//		IConvert<String, Something> converter3 = Something::new;
//		Something something2 = converter3.convert("constructors");
//		System.out.println(something2.endWith("ZZG"));
		
//		Function<String, String> func = Function.identity();
		Function<String, String> func = s -> s;
		System.out.println(func.apply("s"));
		
	}
}

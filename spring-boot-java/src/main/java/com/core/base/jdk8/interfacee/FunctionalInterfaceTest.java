package com.core.base.jdk8.interfacee;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class FunctionalInterfaceTest {

	public static void main(String[] args) {
		
//		// 1、lambda表达式
//		IConvert<String, String> convert0 = p -> p + "s";
//		System.out.println(convert0.convert("123"));
//		
//		// 2、方法引用
//		// 2.1 静态方法引用
//		// 相当于为IConvert创建了一个实现类，重写了convert方法，方法实现是Something类的startsWith方法
		IConvert<String, String> convert = Something::startsWith;
		System.out.println(convert.convert("123"));
//				
		// 2.2 实例方法引用
		Something something = new Something();
		IConvert<String, String> converter2 = something::endWith;
		System.out.println(converter2.convert("实例方法"));
		
		// 自动把数据参数作为实例，调用实例方法(输入参数是实例，默认可以省略)
		IConvert<Something, String> converter22 = Something::endWith;
		System.out.println(converter22.convert(something));
		
		Function<String, Integer> func = String::length;
		System.out.println(func.apply("test length"));
		
		BiConsumer<List<String>, String> bicons = List::add;
		List<String> list = new ArrayList<>();
		bicons.accept(list, "123");
		System.out.println();
//		
//		// 2.3 构造方法引用
		// 构造方法可以看作特殊的静态方法
		IConvert<String, Something> converter3 = Something::new;
		Something something2 = converter3.convert("constructors");
		System.out.println(something2.endWith("ZZG"));
		
		
	}
}

package com.jvm.memory;

import java.util.ArrayList;
import java.util.List;

import javax.management.StringValueExp;

/**
 * 
 * 运行时常量池导致的内存溢出异常OutOfMemoryError 
 * -XX:PermSize=10M -XX:MaxPermSize=10M
 * 
 * jdk1.6:
 * 		Exception in thread "main" java.lang.OutOfMemoryError: PermGen space
 * jdk1.7：
 * 		常量池不在方法区，所以运行正常
 * 
 * @author zhangzhigang
 *
 */
public class RuntimeConstantPoolOOM {

	public static void main(String[] args) throws Exception {
		test();
	}
	
	public static void oomTest() {
		// 使用List保持着常量池引用，避免Full GC回收常量池行为
		List<String> list = new ArrayList<>();
		// 10MB的PermSize在integer范围内足够产生OOM了
		int i = 0;
		while(true) {
			list.add(String.valueOf(i++).intern());
		}
	}
	
	public static void test() {
		String str1 = new StringBuilder("计算机").append("软件").toString();
		System.out.println(str1.intern() == str1);
		
		String str2 = new StringBuilder("ja").append("va").toString();
		System.out.println(str2.intern() == str2);
	}
	
	
}

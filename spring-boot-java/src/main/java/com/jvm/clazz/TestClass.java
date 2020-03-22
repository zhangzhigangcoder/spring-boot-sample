package com.jvm.clazz;

/**
 * 
 * 测试Class文件结构 用WinHex工具打开
 * 
 * @author zhangzhigang
 *
 */
public class TestClass extends SuperClass {
	
	private static int m = 123;
	
	private int n = 234;
	
	public static final int mm = 666;
	
	public int inc() {
		System.out.println(s);
		return m + 1;
	}
	
}

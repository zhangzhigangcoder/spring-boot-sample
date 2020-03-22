package com.jvm.monitor;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 测试VisualVM中BTrace功能
 * 
 * @author zhangzhigang
 *	
 */
public class BTraceTest {
	
	public static void main(String[] args) throws Exception {
		BTraceTest test = new BTraceTest();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		for (int i=0; i<10; i++) {
			reader.readLine();
			int a = (int) Math.round(Math.random() * 1000);
			int b = (int) Math.round(Math.random() * 1000);
			System.out.println(test.add(a, b));
		}
	}
	
	public int add(int a, int b) {
		return a + b;
	}
	
}
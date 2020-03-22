package com.design.creational.singleton;

public class Test {

	public static void main(String[] args) {
		Long start = System.currentTimeMillis();
		for(int i=0; i<100000; i++) {
			DoubleCheckThreadSafeSingleton.getInstance();
		}
		System.out.println(System.currentTimeMillis() - start);
		
		Long start2 = System.currentTimeMillis();
		for(int i=0; i<100000; i++) {
			HighPerformanceDoubleCheckThreadSafeSingleton.getInstance();
		}
		System.out.println(System.currentTimeMillis() - start2);
		
	}
	
	
}

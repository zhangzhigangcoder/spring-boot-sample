package com.design.creational.singleton;

public class HighPerformanceDoubleCheckThreadSafeSingleton {

	// volatile 可以预防创建对象时指令重排
	private static volatile HighPerformanceDoubleCheckThreadSafeSingleton instance;
	
	private HighPerformanceDoubleCheckThreadSafeSingleton() {}
	
	public static synchronized HighPerformanceDoubleCheckThreadSafeSingleton getInstance() {
		HighPerformanceDoubleCheckThreadSafeSingleton result = instance;
		if (null == result) {
			synchronized (HighPerformanceDoubleCheckThreadSafeSingleton.class) {
				result = instance;
				if (null == result) {
					instance = result = new HighPerformanceDoubleCheckThreadSafeSingleton();
				}
			}
		}
		return result;
	}
}

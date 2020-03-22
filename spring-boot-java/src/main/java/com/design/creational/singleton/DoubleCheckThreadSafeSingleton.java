package com.design.creational.singleton;

public class DoubleCheckThreadSafeSingleton {

	// volatile 可以预防创建对象时指令重排
	private static volatile DoubleCheckThreadSafeSingleton instance;
	
	private DoubleCheckThreadSafeSingleton() {}
	
	public static synchronized DoubleCheckThreadSafeSingleton getInstance() {
		if (null == instance) {
			synchronized (DoubleCheckThreadSafeSingleton.class) {
				if (null == instance) {
					instance = new DoubleCheckThreadSafeSingleton();
				}
			}
		}
		return instance;
	}
}

package com.core.thread.concurrent.synchronize;

/**
 * @author zhangzhigang
 *
 */
public class SynchronizedLockTest {

	private static final Object lock = new Object();
	
	public synchronized void m1() {
	}

	public static synchronized void m2() {
	}

	public void m3() {
		synchronized (this) {
		}
	}

	public void m4() {
		synchronized (SynchronizedLockTest.class) {
		}
	}

	public void m5() {
		synchronized (lock) {
		}
	}




}


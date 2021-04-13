package com.core.thread.concurrent.locks;

import java.util.concurrent.locks.LockSupport;

public class LockSupportTest {
	public static void main(String[] args) throws InterruptedException {
//		LockSupport.park();
//		LockSupport.park();
//		LockSupport.getBlocker(new Thread());
//		LockSupport.parkNanos(0);
//		LockSupport.parkUntil(1617028883000L);

//		Thread th = new Thread(new Runnable() {
//			@Override
//			public void run() {
//				System.out.println("--111--");
				LockSupport.park();
//				LockSupport.park();
//				System.out.println("--222--");
//			}
//		});
//		th.start();
//		Thread.sleep(1000);
//		LockSupport.unpark(th);
//		Thread.sleep(1000);
//		LockSupport.unpark(th);
		System.out.println("----");
	}
}

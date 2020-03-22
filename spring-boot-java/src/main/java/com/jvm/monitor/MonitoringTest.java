package com.jvm.monitor;

/**
 * 用jconsole工具排查死锁问题
 * 
 * @author zhangzhigang
 *	
 */
public class MonitoringTest {
	
	public static void main(String[] args) throws Exception {
		// 活锁等待
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		br.readLine();
//		createBusyThread();
//		br.readLine();
//		
//		Object obj = new Object();
//		createLockThread(obj);
		
		// 死锁等待
		for(int i=0; i<100; i++) {
			new Thread(new SynAddRunable(1, 2)).start();
			new Thread(new SynAddRunable(2,1)).start();
//			System.out.println(Integer.valueOf(128) == 128);
		}
	}
	
	/**
	 * 线程死循环演示
	 */
	public static void createBusyThread() {
		new Thread(() -> {
			while(true) {}
		}, "testBusyThread").start(); 
	}
	
	/**
	 * 线程等待演示
	 * @param lock
	 */
	public static void createLockThread(final Object lock) {
		new Thread(() -> {
			synchronized (lock) {
				try {
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "testLockThread").start(); 
	}
	
	/**
	 * 线程死锁演示
	 * @author qw
	 *
	 */
	static class SynAddRunable implements Runnable {

		int a,b;
		int c;
		public SynAddRunable(int a, int b) {
			this.a = a;
			this.b = b;
		}
		
		@Override
		public void run() {
			synchronized (Integer.valueOf(a)) {
				synchronized (Integer.valueOf(b)) {
					System.out.println(a + b);
				}
			}
		} 
		
		
	}
}

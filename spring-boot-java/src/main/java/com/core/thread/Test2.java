package com.core.thread;

/**
 * @author zhangzhigang
 *
 */
public class Test2 {
	private static int num = 0, num2 = 0;
	private static volatile boolean flag = false;

	public static synchronized void write() {
		flag = true;
	}

	public static synchronized void read() {
		if (flag) {
			System.out.println(flag);
		}

	}

	public static void main(String[] args) throws InterruptedException {
//		for (int j = 0; j < 10; j++) {
//			for (int i = 0; i < 1000; i++) {
//				new Thread(() -> {
//					flag = false;
//					read();
//				}).start();
//			}

			new Thread(() -> {
				while (true) {
					// 耗时操作可以强制刷新整个工作内存
//					System.out.println("-----------" + num);
					// volatile关键字可以强制刷新整个工作内存
					if (flag && num == 1) {
						break;
					}
				}

			}).start();


		Thread.sleep(2000);
//		synchronized (Test.class) {
			flag = true;
			num = 1;
			System.out.println("flag is set true");
//		}


//		}

	}
}
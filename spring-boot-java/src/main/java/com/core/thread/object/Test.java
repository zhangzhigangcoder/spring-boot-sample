package com.core.thread.object;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Test {
	
	public static void main(String[] args) throws Exception {
		Message msg = new Message("process it");
		Waiter waiter = new Waiter(msg);
		new Thread(waiter, "waiter").start();
		
		Waiter waiter2 = new Waiter(msg);
		new Thread(waiter2, "waiter2").start();
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		reader.readLine();
		
		Notifier notifier = new Notifier(msg);
		new Thread(notifier, "notifier").start();
		System.out.println("All the threads are started");
//		threadTest();
	}
	
	/**
	 * As a thread terminates the this.notifyAll method is invoked. 
	 * It is recommended that applications not use wait, 
	 * notify, or notifyAll on Thread instances.
	 */
	public static void threadTest() {
		Thread t = new Thread(() -> {
			try {
				System.out.println(" t thread sleep start.");
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(" t thread sleep end.");
		});
		t.start();
		
		Thread t2 = new Thread(() -> {
			synchronized (t) {
				try {
					String name = Thread.currentThread().getName();
					System.out.println(name + " wait start.");
					t.wait();
					System.out.println(name + " wait end.");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		},"t1");
		t2.start();
		
		Thread t3 = new Thread(() -> {
			synchronized (t) {
				try {
					String name = Thread.currentThread().getName();
					System.out.println(name + " wait start.");
					t.wait();
					System.out.println(name + " wait end.");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		},"t2");
		t3.start();
		
		
		
	}
}

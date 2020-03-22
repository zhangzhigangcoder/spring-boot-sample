package com.core.thread.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author zhangzhigang
 *
 */
public class FutureTaskTest {
	
	public static void main(String[] args) {
		MyCallable callable1 = new MyCallable(1000);
		MyCallable callable2 = new MyCallable(20);
		
		FutureTask<String> futureTask1 = new FutureTask<>(callable1);
		FutureTask<String> futureTask2 = new FutureTask<>(callable2);
		
		ExecutorService executor = Executors.newFixedThreadPool(2);
		executor.execute(futureTask1);
		executor.execute(futureTask2);
		
		while(true) {
			try {
				if (futureTask1.isDone() && futureTask2.isDone()) {
					System.out.println("Done");
					// shut down executor service
					executor.shutdown();
					return;
				}
				if (!futureTask1.isDone()) {
					// wait indefinitely for future task to complete
					System.out.println("FutureTask1 output= " + futureTask1.get());
				}
				System.out.println("Waiting for FutureTask2 to complete");
				String s = futureTask2.get(200L, TimeUnit.MICROSECONDS);
				if (null != s) {
					System.out.println("FutureTask2 output= " + s);
				}
			} catch (InterruptedException | ExecutionException | TimeoutException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	static class MyCallable implements Callable<String> {

		private long waitTime;
		
		public MyCallable(int timeInMillis) {
			this.waitTime = timeInMillis;
		}
		
		@Override
		public String call() throws Exception {
			Thread.sleep(waitTime);
			// return the thread name executing this callable task
			return Thread.currentThread().getName();
		}
		
	}
}


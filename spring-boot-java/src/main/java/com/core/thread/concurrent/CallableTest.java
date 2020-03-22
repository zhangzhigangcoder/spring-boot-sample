package com.core.thread.concurrent;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author zhangzhigang
 *
 */
public class CallableTest {
	
	
	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(10);
		List<Future<String>> list = new ArrayList<>();
		Callable<String> callable =  () -> {
			Thread.sleep(1000);
			// return the thread name executing this callable task
			return Thread.currentThread().getName();
		};
		
		for(int i=0; i<100; i++) {
			// submit Callable tasks to be executed by thread pool
			Future<String> future = executor.submit(callable);
			// add Future to the list, we can get return value using Future
			list.add(future);
		}
		
		list.forEach(fut -> {
			try {
				// print the return value of Future, notice the output delay in console
				// because Future.get() waits for task to get completed
				System.out.println(new Date() + ": " + fut.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		});
		// shut down the executor service now
		executor.shutdown();
	}
	
}


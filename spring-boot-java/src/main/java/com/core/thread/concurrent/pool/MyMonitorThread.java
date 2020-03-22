package com.core.thread.concurrent.pool;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author zhangzhigang
 *
 */
public class MyMonitorThread implements Runnable {

	private ThreadPoolExecutor executor;
	private int seconds;
	private boolean run = true;
	
	public MyMonitorThread(ThreadPoolExecutor executor, int delay) {
		this.executor = executor;
		this.seconds = delay;
	}
	
	public void shutdown() {
		this.run = false;
	}
	
	@Override
	public void run() {
		while(run) {
			System.out.println(String.format("[monitor] [%d/%d] Active: %d, Completed: %d, Task: %d, isShutdown: %s, isTerminated: %s",
				this.executor.getPoolSize(), // workers size
				this.executor.getCorePoolSize(), // corePoolSize
				this.executor.getActiveCount(),// running workers size
				this.executor.getCompletedTaskCount(),
				this.executor.getTaskCount(), // completed task size + running worker size + wait run task size
				this.executor.isShutdown(),
				this.executor.isTerminated()
			));
			
			try {
				Thread.sleep(seconds * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}


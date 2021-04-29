package com.core.thread.concurrent.pool;

import java.util.concurrent.*;

/**
 * schedule: 只执行一次
 * scheduleAtFixedRate: 周期执行，下一次执行时间 = delay + 前一个任务执行开始时间
 * scheduleWithFixedDelay: 周期执行，下一次执行时间 = delay + 前一个任务执行结束时间
 * @author zhangzhigang
 *
 */
@SuppressWarnings("unused")
public class ScheduledThreadPoolExecutorTest {
	
	public static void main(String[] args) {
		 final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
		executor.schedule(() -> {
			System.out.println("task executed.");
		}, 5, TimeUnit.SECONDS);
		executor.shutdown();
		System.out.println("execute finish");
	}

}


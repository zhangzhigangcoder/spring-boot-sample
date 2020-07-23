package com.system.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

/**
 * 高并发-异步-线程池
 * 
 * 自定义线程池
 * 
 * 1. 使用有界队列的固定数量线程池
 * 2. 拒绝策略是将任务丢弃，但需要记录错误日志
 * 3. 使用一个调度线程池对业务线程进行监控
 *
 */
public class AsyncThreadExecutor implements AutoCloseable {
	private static final int DEFAULT_QUEUE_SIZE = 1000;
	private static final int DEFAULT_POOL_SIZE = 10;
	
	private int queueSize = DEFAULT_QUEUE_SIZE;
	private int poolSize = DEFAULT_POOL_SIZE;
	
	/**
	 * 周期性监控线程池的运行状态
	 */
	private final ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor(
			new BasicThreadFactory
			.Builder()
			.namingPattern("async thread executor monitor")
			.build());
	
	/**
	 * 自定义异步线程池
	 * 1. 使用有界队列
	 * 2. 自定义拒绝策略
	 */
	private final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
			poolSize, 
			poolSize, 
			0, 
			TimeUnit.MILLISECONDS, 
			new ArrayBlockingQueue<>(queueSize),
			new BasicThreadFactory
			.Builder()
			.namingPattern("async-thread-%d")
			.build(), 
			(r, executor) -> { System.out.println("the async executor pool is full!!"); });
	
	private final ExecutorService executorService = threadPoolExecutor;
	
	public void init() {
		scheduledExecutorService.scheduleAtFixedRate(() -> {
			// 线程持需要执行的任务数
			long taskCount = threadPoolExecutor.getTaskCount();
			
			// 线程池在运行过程中已完成的任务数
			long completedTaskCount = threadPoolExecutor.getCompletedTaskCount();
			
			// 曾经创建过的最大线程数
			int largestPoolSize = threadPoolExecutor.getLargestPoolSize();
			
			// 线程池中的线程数量
			int poolSize = threadPoolExecutor.getPoolSize();
			
			// 线程池中活跃的线程数量
			int activeCount = threadPoolExecutor.getActiveCount();
			
			System.out.println(String.format("async-executor monitor. taskCount:%s, completedTaskCount:%s, largestPoolSize:%s, poolSize:%s, activeCount:%s", taskCount, completedTaskCount, largestPoolSize, poolSize, activeCount));
		
		}, 0, 10, TimeUnit.MINUTES);
	}
	
	public void execute(Runnable task) {
		executorService.execute(task);
	}
	
	public void setQueueSize(int queueSize) {
		this.queueSize = queueSize;
	}

	public void setPoolSize(int poolSize) {
		this.poolSize = poolSize;
	}

	@Override
	public void close() throws Exception {
		executorService.shutdown();
	}
	
}
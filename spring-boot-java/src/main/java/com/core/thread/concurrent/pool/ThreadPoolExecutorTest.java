package com.core.thread.concurrent.pool;

import java.util.concurrent.*;

/**
 * @author zhangzhigang
 *
 */
@SuppressWarnings("unused")
public class ThreadPoolExecutorTest {
	
	/**
	 * 1.添加任务顺序
	 *    a.the number of threads less than corePoolSize
	 *     	创建线程(worker)执行任务
	 * 	  b. the number of threads equals corePoolSize
	 * 		将任务添加到队列中
	 * 	  c.queue is full and the number of threads less than maximumPoolSize 
	 * 		创建线程(work)执行任务
	 * 	  d.以上条件都不满足，调用reject方法
	 * 
	 * 2.线程销毁
	 * 	 boolean timed = allowCoreThreadTimeOut || wc > corePoolSize;
	 *
     *      if ((wc > maximumPoolSize || (timed && timedOut))
     *           && (wc > 1 || workQueue.isEmpty())) {
     *          if (compareAndDecrementWorkerCount(c))
     *               return null;
     *           continue;
     *       }
     *
     *       try {
     *           Runnable r = timed ?
     *               workQueue.poll(keepAliveTime, TimeUnit.NANOSECONDS) :
     *               workQueue.take();
     *           if (r != null)
     *               return r;
     *           timedOut = true;
	 * 
	 * 
	 * 
	 */
	
	public static void main(String[] args) {
		submitCallableTest();
	}

	public static void test1() {
		RejectedExecutionHandler rejectionHandler = new RejectedExecutionHandlerImpl();
		ThreadFactory threadFactor = Executors.defaultThreadFactory();
		ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 4, 10, TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>(2), threadFactor, rejectionHandler);
//		executor.allowCoreThreadTimeOut(true);
		MyMonitorThread monitor = new MyMonitorThread(executor, 3);
		new Thread(monitor).start();
		for (int i = 0; i < 10; i++) {
			executor.execute(new WorkerThread("cmd-" + i));
		}
//		Thread.sleep(3 * 1000);
//		executor.shutdown();
		while(!executor.isTerminated()) {}
//		Thread.sleep(5 * 1000);
//		monitor.shutdown();
		System.out.println("Finished all threads");
	}
	
	public static void simpleTest() {
		ExecutorService executor = Executors.newFixedThreadPool(5);
//		ExecutorService executor = new ThreadPoolExecutor(2, 45, 1000 * 60L, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>(5));
		for (int i = 0; i < 10; i++) {
			Runnable worker = new WorkerThread("" + i);
			executor.execute(worker);
		}
		executor.shutdown();
		while(!executor.isTerminated()) {}
		System.out.println("Finished all threads");
	}

	public static void submitRunnableTest() {
		ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 4, 10, TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>(2));
		Future<?> future = executor.submit(new WorkerThread("task Thread"), "success");
		try {
			String result = (String) future.get();
			System.out.println(result);
			executor.shutdown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

	public static void submitCallableTest() {
		ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 4, 10, TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>(2));
		Future<?> future = executor.submit(() -> "success");
		try {
			String result = (String) future.get();
			System.out.println(result);
			executor.shutdown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}



}


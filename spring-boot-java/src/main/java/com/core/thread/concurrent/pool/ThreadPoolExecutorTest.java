package com.core.thread.concurrent.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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
	
	public static void main(String[] args) throws Exception {
		RejectedExecutionHandler rejectionHandler = new RejectedExecutionHandlerImpl();
		ThreadFactory threadFactor = Executors.defaultThreadFactory();
		ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 4, 10, TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>(2), threadFactor, rejectionHandler);
		MyMonitorThread monitor = new MyMonitorThread(executor, 3);
		new Thread(monitor).start();
		for (int i = 0; i < 10; i++) {
			executor.execute(new WorkerThread("cmd-" + i));
		}
		Thread.sleep(30 * 1000);
		executor.shutdown();
		Thread.sleep(5 * 1000);
		monitor.shutdown();
	}
	
	public static void simpleTest(String[] args) {
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

	public static void test() {
		int temp = 0;
		int count_bits = Integer.SIZE - 3;
		// 536870911 11111111111111111111111111111(29)
		int capacity = (1 << count_bits) -1;
		// -536870912 11100000000000000000000000000000(32)
		int running = -1 << count_bits;
		// 0
		int shutdown = 0 << count_bits;
		// 536870912 100000000000000000000000000000(30)
		int stop       =  1 << count_bits;
		// 1073741824 1000000000000000000000000000000(31)
		int tidying    =  2 << count_bits;
		// 1610612736 1100000000000000000000000000000
		int terminated       =  3 << count_bits;
		temp = (running | 0) & capacity;
		System.out.println(temp);
		System.out.println(Integer.toBinaryString(temp));
	}
}


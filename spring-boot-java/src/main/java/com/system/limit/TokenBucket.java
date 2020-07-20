package com.system.limit;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.google.common.util.concurrent.RateLimiter;

/**
 * 限流-令牌桶算法
 * 
 * RateLimiter.create(10); 普通限制
 * RateLimiter.create(10, 10, TimeUnit.SECONDS); 添加预热时间处理
 * 
 * https://blog.csdn.net/forezp/article/details/100060686
 *
 */
public class TokenBucket {
	
	public static void main(String[] args) {
		user_request_limit();
	}
	
	public static void user_request_limit() {
		RateLimiter limiter = RateLimiter.create(1); // 每秒处理1个
		List<Runnable> taskList = new ArrayList<>();
		for (int i = 1; i <= 10; i++) {
			taskList.add(new UserRequest(i));
		}
		ExecutorService threadPool = Executors.newCachedThreadPool();
		for (Runnable run : taskList) {
			// 获取不到，会阻塞
			// 并发量高时，用户请求可能会等待很久
			System.out.println("等待时间：" + limiter.acquire());
			threadPool.execute(run);
		}
		threadPool.shutdown();
	}
	
	public static void user_request_limit2() {
		RateLimiter limiter = RateLimiter.create(1); // 每秒处理1个
		List<Runnable> taskList = new ArrayList<>();
		for (int i = 1; i <= 10; i++) {
			taskList.add(new UserRequest(i));
		}
		ExecutorService threadPool = Executors.newCachedThreadPool();
		for (Runnable run : taskList) {
			// 获取不到，返回false
			if (!limiter.tryAcquire(1000, TimeUnit.MILLISECONDS)) {
				// 立即返回
				// 或异步处理
			} else {
				// 直接处理
				threadPool.execute(run);
			}
			
		}
		threadPool.shutdown();
	}
	
	/**
	 * 模拟用户请求
	 *
	 */
	private static class UserRequest implements Runnable {
		private int id;
		
		public UserRequest(int id) {
			this.id = id;
		}
		
		@Override
		public void run() {
			System.out.println(id);
		}
		
	}
}
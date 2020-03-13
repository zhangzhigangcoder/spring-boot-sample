package org.spring.boot.task;

import java.util.Random;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

/**
 * 单线程执行
 * @author zhangzhigang
 *
 */
@Component
public class FutureAsyncTask {

	private static final Logger log = LoggerFactory.getLogger(FutureAsyncTask.class);
	
	public static Random random = new Random();
	
	@Async
	public Future<String> doTaskOne() throws Exception {
		log.info("开始做任务一");
		long start = System.currentTimeMillis();
		Thread.sleep(random.nextInt(10000));
		long end = System.currentTimeMillis();
		log.info("完成任务一，耗时：" + (end - start) + "毫秒");
		return new AsyncResult<String>("任务一完成");
	}
	
	@Async
	public Future<String> doTaskTwo() throws Exception {
		log.info("开始做任务二");
		long start = System.currentTimeMillis();
		Thread.sleep(random.nextInt(10000));
		long end = System.currentTimeMillis();
		log.info("完成任务二，耗时：" + (end - start) + "毫秒");
		return new AsyncResult<String>("任务二完成");
	}
	
	@Async
	public Future<String> doTaskThree() throws Exception {
		log.info("开始做任务三");
		long start = System.currentTimeMillis();
		Thread.sleep(random.nextInt(10000));
		long end = System.currentTimeMillis();
		log.info("完成任务三，耗时：" + (end - start) + "毫秒");
		return new AsyncResult<String>("任务三完成");
	}
	
}

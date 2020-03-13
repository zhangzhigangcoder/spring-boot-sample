package org.spring.boot.test;

import java.util.concurrent.Future;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.spring.boot.task.AsyncTask;
import org.spring.boot.task.FutureAsyncTask;
import org.spring.boot.task.Task;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {
	
	@Resource
	private Task task;
	
	@Resource
	private AsyncTask asyncTack;
	
	@Resource
	private FutureAsyncTask futureTack;

	@Test
	public void test() throws Exception {
		task.doTaskOne();
		task.doTaskTwo();
		task.doTaskThree();
	}
	
	@Test
	public void asyncTest() throws Exception {
		asyncTack.doTaskOne();
		asyncTack.doTaskTwo();
		asyncTack.doTaskThree();
	}
	
	@Test
	public void futureTest() throws Exception {
		
		long start = System.currentTimeMillis();
		
		Future<String> task1 = futureTack.doTaskOne();
		Future<String> task2 = futureTack.doTaskTwo();
		Future<String> task3 = futureTack.doTaskThree();
		while(true) {
			if(task1.isDone() && task2.isDone() && task3.isDone()) {
				break;
			}
			Thread.sleep(1000);
		}
		long end = System.currentTimeMillis();
		
		System.out.println("任务全部完成，总耗时：" + (end - start) + "毫秒");
	}

}

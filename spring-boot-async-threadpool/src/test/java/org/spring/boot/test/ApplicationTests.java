package org.spring.boot.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.spring.boot.task.AsyncTask;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {
	
	
	@Resource
	private AsyncTask asyncTack;
	
	@Test
	public void asyncTest() throws Exception {
		asyncTack.doTaskOne();
		asyncTack.doTaskTwo();
		asyncTack.doTaskThree();
	}
	

}

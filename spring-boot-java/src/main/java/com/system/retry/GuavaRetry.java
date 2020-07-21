package com.system.retry;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.github.rholder.retry.RetryException;
import com.github.rholder.retry.Retryer;
import com.github.rholder.retry.RetryerBuilder;
import com.github.rholder.retry.StopStrategies;
import com.github.rholder.retry.WaitStrategies;
import com.google.common.base.Predicates;

/**
 * 重试
 * 
 *
 */
public class GuavaRetry {
	
	public static void main(String[] args) {
		// 重试策略
		Retryer<Integer> retryer = RetryerBuilder.<Integer>newBuilder()
			  .retryIfResult(Predicates.isNull())
			  .retryIfResult(Predicates.equalTo(2))
			  .retryIfExceptionOfType(IOException.class)
			  .withStopStrategy(StopStrategies.stopAfterAttempt(3))
			  .withWaitStrategy(WaitStrategies.fixedWait(300, TimeUnit.MILLISECONDS))
			  .build();
		
		// 业务逻辑
		Callable<Integer> task = new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				System.out.println("call");
				return 1;
			}
		};
		
		try {
			Integer result = retryer.call(task);
			System.out.println(result);
		} catch (ExecutionException | RetryException e) {
			e.printStackTrace();
		}
		
	}
	
}
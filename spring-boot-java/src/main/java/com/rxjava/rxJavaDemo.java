package com.rxjava;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * 
 * rxjava demo
 * 参考：https://www.jianshu.com/p/f878a4841a95
 * 
 * @author zhangzhigang
 *
 */
public class rxJavaDemo {
	
	private static String value = "100";

	static {
		PropertyConfigurator.configure("src/main/resources/config/log4j.properties");
	}
	
	private static Logger logger = Logger.getLogger(rxJavaDemo.class);

	public static void main(String[] args) {
		test10();
	}

	/**
	 * 基础使用
	 * 被观察者事件说明：
	 * 		1. onNext()  发送该事件时，观察者会回调onNext()方法
	 * 		2. onError() 发送该事件时，观察者会回调onError()方法，当发送该事件之后，其它事件将不会继续发送
	 * 		3. onComplete() 发送该事件，观察者会回调onComplete()方法，当发送该事件之后，其它事件将不会继续发送
	 */
	public static void test1() {
		// 1. 小说(被观察者)
		// create方法用于创建一个Observable
		Observable<String> novel = Observable.create(new ObservableOnSubscribe<String>() {

			@Override
			public void subscribe(ObservableEmitter<String> emitter) throws Exception {
				emitter.onNext("连载1");
				emitter.onNext("连载2");
//				Exception ex = new IllegalArgumentException("exception");
//				emitter.onError(ex);
				emitter.onNext("cancel");
				emitter.onNext("连载3");
				emitter.onComplete();
			}
		});

		// 2. 读者(观察者)
		Observer<String> reader = new Observer<String>() {

			Disposable disposable = null;

			@Override
			public void onSubscribe(Disposable d) {
				disposable = d;
				logger.debug("onSubscribe");
			}

			@Override
			public void onNext(String t) {
				if ("cancel".equals(t)) { // 取消订阅，后面的“连载3”不会再收到
					disposable.dispose();
					return;
				}
				logger.debug("onNext: " + t);
			}

			@Override
			public void onError(Throwable e) {
				logger.debug("onError: " + e.getMessage());
			}

			@Override
			public void onComplete() {
				logger.debug("onComplete");
			}

		};

		// 3. 读者订阅小说
		novel.subscribe(reader);
	}

	/**
	 * 对test1的简写
	 */
	public static void test1_1() {
		// 1. 小说(被观察者)
		Observable.create(new ObservableOnSubscribe<String>() {

			@Override
			public void subscribe(ObservableEmitter<String> emitter) throws Exception {
				emitter.onNext("连载1");
				emitter.onNext("连载2");
				emitter.onNext("cancel");
				emitter.onNext("连载3");
				emitter.onComplete();
			}
		})
		// 3. 读者订阅小说
		.subscribe(new Observer<String>() {
			// 2. 读者(观察者)
			Disposable disposable = null;

			@Override
			public void onSubscribe(Disposable d) {
				disposable = d;
				logger.debug("onSubscribe");
			}

			@Override
			public void onNext(String t) {
				if ("cancel".equals(t)) { // 取消订阅，后面的“连载3”不会再收到
					disposable.dispose();
					return;
				}
				logger.debug("onNext: " + t);
			}

			@Override
			public void onError(Throwable e) {
				logger.debug("onError: " + e.getMessage());
			}

			@Override
			public void onComplete() {
				logger.debug("onComplete");
			}

		});
	}
	
	/**
	 * just()测试
	 */
	public static void test3() {
		// 1. 小说(被观察者)
		Observable.just("1", "2", "3")
		// 3. 读者订阅小说
		.subscribe(new Observer<String>() {
			// 2. 读者(观察者)
			Disposable disposable = null;

			@Override
			public void onSubscribe(Disposable d) {
				disposable = d;
				logger.debug("onSubscribe");
			}

			@Override
			public void onNext(String t) {
				if ("cancel".equals(t)) { // 取消订阅，后面的“连载3”不会再收到
					disposable.dispose();
					return;
				}
				logger.debug("onNext: " + t);
			}

			@Override
			public void onError(Throwable e) {
				logger.debug("onError: " + e.getMessage());
			}

			@Override
			public void onComplete() {
				logger.debug("onComplete");
			}

		});
	}

	/**
	 * fromArray()测试
	 */
	public static void test4() {
		// 1. 小说(被观察者)
		String[] arrays = new String[] {"1", "2", "3"};
		Observable.fromArray(arrays)
		// 3. 读者订阅小说
		.subscribe(new Observer<String>() {
			// 2. 读者(观察者)
			Disposable disposable = null;

			@Override
			public void onSubscribe(Disposable d) {
				disposable = d;
				logger.debug("onSubscribe");
			}

			@Override
			public void onNext(String t) {
				if ("cancel".equals(t)) { // 取消订阅，后面的“连载3”不会再收到
					disposable.dispose();
					return;
				}
				logger.debug("onNext: " + t);
			}

			@Override
			public void onError(Throwable e) {
				logger.debug("onError: " + e.getMessage());
			}

			@Override
			public void onComplete() {
				logger.debug("onComplete");
			}

		});
	}
	
	/**
	 * fromCallable()测试
	 * call方法会在观察者订阅的时候调用，并传递到随后的onNext方法中
	 */
	public static void test5() {
		// 1. 小说(被观察者)
		Observable.fromCallable(new Callable<String>() {

			@Override
			public String call() throws Exception {
				return "1";
			}
		
		})
		// 3. 读者订阅小说
		.subscribe(new Observer<String>() {
			// 2. 读者(观察者)
			Disposable disposable = null;

			@Override
			public void onSubscribe(Disposable d) {
				disposable = d;
				logger.debug("onSubscribe");
			}

			@Override
			public void onNext(String t) {
				if ("cancel".equals(t)) { // 取消订阅，后面的“连载3”不会再收到
					disposable.dispose();
					return;
				}
				logger.debug("onNext: " + t);
			}

			@Override
			public void onError(Throwable e) {
				logger.debug("onError: " + e.getMessage());
			}

			@Override
			public void onComplete() {
				logger.debug("onComplete");
			}

		});
	}
	
	/**
	 * fromFuture()测试
	 * call方法会在观察者订阅的时候调用，并传递到随后的onNext方法中
	 */
	public static void test6() {
		// 1. 小说(被观察者)
		final FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {

			@Override
			public String call() throws Exception {
				return "100";
			}
		});
		
		Observable.fromFuture(futureTask).doOnSubscribe(new Consumer<Disposable>() {

			@Override
			public void accept(Disposable t) throws Exception {
				futureTask.run();
			}
		
		})
		// 3. 读者订阅小说
		.subscribe(new Consumer<String>() {

			@Override
			public void accept(String t) throws Exception {
				logger.debug("accept: " + t);
			}
		});
	}
	
	/**
	 * fromIterable()测试
	 */
	public static void test7() {
		// 1. 小说(被观察者)
		List<String> list = Arrays.asList("1","2","3");
		Observable.fromIterable(list)
		// 3. 读者订阅小说
		.subscribe(new Consumer<String>() {

			@Override
			public void accept(String t) throws Exception {
				logger.debug("accept: " + t);
			}
		});
	}
	
	/**
	 * defer()测试
	 * defer操作符会一直等待，直到有观察者订阅它，然后使用Observable工厂方法生成一个新的Observable。
	 * 因此，每次订阅操作订阅的被观察者都是不一样的，这样可以确保Observable包含最新的数据
	 */
	public static void test8() {
		// 1. 小说(被观察者)
		Observable<String> observable = Observable.defer(new Callable<ObservableSource<String>>() {

			@Override
			public ObservableSource<String> call() throws Exception {
				return Observable.just(value);
			}
			
		});
		
		value = "200";
		
		// 3. 读者订阅小说
		observable.subscribe(new Consumer<String>() {

			@Override
			public void accept(String t) throws Exception {
				logger.debug("accept: " + t);
			}
		});
		
		value = "300";
		
		// 3. 读者订阅小说
		observable.subscribe(new Consumer<String>() {

			@Override
			public void accept(String t) throws Exception {
				logger.debug("accept: " + t);
			}
		});
	}
	
	/**
	 * timer()测试
	 * 延迟指定时间后会发送一个大小为0L的值给观察者
	 */
	public static void test9() {
		Observable.timer(2, TimeUnit.SECONDS)
			.subscribe(new Consumer<Long>() {

				@Override
				public void accept(Long t) throws Exception {
					logger.debug("accept: " + t);
				}
			});
	}
	
	/**
	 * interval()测试
	 * 每隔一段时间就发送一个事件，传递值从0开始并且不断增1
	 */
	public static void test10() {
		Observable.interval(2, TimeUnit.SECONDS)
			.subscribe(new Consumer<Long>() {

				@Override
				public void accept(Long t) throws Exception {
					logger.debug("accept: " + t);
				}
			});
	}
	
}

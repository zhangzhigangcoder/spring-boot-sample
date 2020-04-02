package com.rxjava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import io.reactivex.Notification;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.BooleanSupplier;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.internal.schedulers.SingleScheduler;
import io.reactivex.observables.GroupedObservable;
import io.reactivex.schedulers.Schedulers;

/**
 * 
 * rxjava2 demo
 * 
 * github: https://github.com/ReactiveX/RxJava
 * 
 * 	参考：
 * 		https://www.jianshu.com/p/f878a4841a95
 *  	https://www.jianshu.com/p/ea110cc6ea39
 * 
 * @author zhangzhigang
 *
 */
public class rxJavaDemo {

	static {
		PropertyConfigurator.configure("src/main/resources/config/log4j.properties");
	}

	private static Logger logger = Logger.getLogger(rxJavaDemo.class);

	public static void main(String[] args) {
		testJust();
	}
	
	/**************************创建操作符*************************/

	/**
	 * 基础使用 被观察者事件说明： 
	 * 	1. onNext() 发送该事件时，观察者会回调onNext()方法 
	 * 	2. onError()
	 * 		发送该事件时，观察者会回调onError()方法，当发送该事件之后，其它事件将不会继续发送 
	 * 	3. onComplete()
	 * 		发送该事件，观察者会回调onComplete()方法，当发送该事件之后，其它事件将不会继续发送
	 */
	public static void testCreate() {
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
	 * 对testCreate的简写
	 */
	public static void testCreateShort() {
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
	 * 创建一个 Observable并发送事件，发送的事件总数不可以超出十个
	 */
	public static void testJust() {
		Observable.just("1", "2", "3")
			.subscribe(new Observer<String>() {
				
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
	 * 和 just() 类似，但 fromArray 可以传入多于十个的变量，并且可以传入一个数组
	 */
	public static void testFromArray() {
		String[] arrays = new String[] { "1", "2", "3" };
		Observable.fromArray(arrays)
			.subscribe(new Observer<String>() {
				
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
	 * 这里的 Callable 是指 java.util.concurrent 中的 Callable，
	 * Callable 和 Runnable 的用法基本一致，只是它包含一个返回值，这个结果值就是发给观察者的
	 */
	public static void testFromCallable() {
		Observable.fromCallable(new Callable<String>() {

			@Override
			public String call() throws Exception {
				return "1";
			}

		})
		.subscribe(new Observer<String>() {
			
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
	 * fromFuture()测试 call方法会在观察者订阅的时候调用，并传递到随后的onNext方法中
	 */
	public static void testFromFuture() {
//		final Future<String> future = Executors.newSingleThreadExecutor().submit(new Callable<String>() {
//
//			@Override
//			public String call() throws Exception {
//				return "100";
//			}
//		});
//		
		final FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {

			@Override
			public String call() throws Exception {
				return "100";
			}
		});

//		// future状态管理交给线程池管理
//		Observable<String> observable = Observable.fromFuture(future);
		
		Observable<String> observable2 = Observable.fromFuture(futureTask)
				.doOnSubscribe(new Consumer<Disposable>() {

					@Override
					public void accept(Disposable t) throws Exception {
						// 手动调用会修改future状态
						futureTask.run();
					}

				});
		
		observable2.subscribe(new Consumer<String>() {

			@Override
			public void accept(String t) throws Exception {
				logger.debug("accept: " + t);
			}
		});
	}

	/**
	 * 用于发送一个 List集合数据给被观察者，数据项逐个发送给观察者
	 */
	public static void testFromIterable() {
		// 1. 小说(被观察者)
		List<String> list = Arrays.asList("1", "2", "3");
		Observable.fromIterable(list)
				// 3. 读者订阅小说
				.subscribe(new Consumer<String>() {

					@Override
					public void accept(String t) throws Exception {
						logger.debug("accept: " + t);
					}
				});
	}

	private static String value = "100";
	/**
	 * defer()测试 defer操作符会一直等待，直到有观察者订阅它，然后使用Observable工厂方法生成一个新的Observable。
	 * 因此，每次订阅操作订阅的被观察者都是不一样的，这样可以确保Observable包含最新的数据
	 */
	public static void testDefer() {
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
	 * timer()测试 延迟指定时间后会发送一个大小为0L的值给观察者
	 */
	public static void testTimer() {
		Observable.timer(2, TimeUnit.SECONDS).subscribe(new Consumer<Long>() {

			@Override
			public void accept(Long t) throws Exception {
				logger.debug("accept: " + t);
			}
		});
	}

	/**
	 * interval()测试 每隔一段时间就发送一个事件，传递值从0开始并且不断增1
	 */
	public static void testInterval() {
		Observable.interval(1, TimeUnit.SECONDS).subscribe(new Consumer<Long>() {

			@Override
			public void accept(Long t) throws Exception {
				logger.debug("accept: " + t);
			}
		});

		try {
			Thread.sleep(10000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 可以指定发送事件的开始值和数量，其他与 interval() 的功能一样
	 */
	public static void testIntervalRange() {
        Observable.intervalRange(2, 3, 3, 1, TimeUnit.SECONDS)
        .subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
                logger.debug("onSubscribe");
            }

            @Override
            public void onNext(Long aLong) {
                logger.debug("onNext：" + aLong);
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
        
        try {
			Thread.sleep(10000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 发送指定范围的事件序列
	 */
	public static void testRange() {
        Observable.range(2, 5)
        .subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                logger.debug("values is :" + integer);
            }
        });
	}
	
	/**
	 * 作用与 range() 一样，只是数据类型是 Long
	 */
	public static void testRangeLong() {
        Observable.rangeLong(2, 5)
            .subscribe(new Consumer<Long>() {
                @Override
                public void accept(Long aLong) {
                    logger.debug("values is :" + aLong);
                }
            });
	}
	
	/**
	 * empty() 直接发送 onComplete() 事件
	 * never() 不会发送任何事件
	 * error() 发送onError事件
	 */
	public static void testEmpty_Never_Error() {
		Observable<Object> empty = Observable.empty();
//		Observable<Object> never = Observable.never();
//		Observable<Object> error = Observable.error(new Throwable("exception"));
		
		empty.subscribe(new Observer<Object>() {
            @Override
            public void onSubscribe(Disposable d) {
                logger.debug("onSubscribe");
            }

            @Override
            public void onNext(Object object) {
                logger.debug("onNext: " + object);
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
	
	/**************************转换操作符*************************/
	
	/**
	 * map() 用于将被观察者发送的数据类型转变成其他的类型
	 */
	public static void testMap() {
		Observable.just(1, 2, 3).map(new Function<Integer, String>() {
			@Override
			public String apply(Integer integer) {
				return "I'm " + integer;
			}
		}).subscribe(new Consumer<String>() {
			@Override
			public void accept(String s) {
				logger.info(s);
			}
		});
	}

	/**
	 * 用于将事件序列中的元素进行整合加工，返回一个新的被观察者
	 */
	public static void testFlatMap() {
		List<List<String>> listArrayList = new ArrayList<>();

		List<String> stringList = new ArrayList<>();
		for (int j = 0; j < 2; j++) {
			stringList.add("A_" + j);
		}
		listArrayList.add(stringList);

		stringList = new ArrayList<>();
		for (int j = 0; j < 2; j++) {
			stringList.add("B_" + j);
		}
		listArrayList.add(stringList);

		Observable.fromIterable(listArrayList).flatMap(new Function<List<String>, ObservableSource<String>>() {
			@Override
			public ObservableSource<String> apply(List<String> stringList1) throws Exception {
				return Observable.fromIterable(stringList1);
			}
		}).subscribe(new Consumer<String>() {
			@Override
			public void accept(String s) throws Exception {
				logger.info("value is: " + s);
			}
		});
	}

	/**
	 * concatMap() 和 flatMap() 基本一样，只不过 concatMap() 转发出来的事件是有序的，而 flatMap() 是无序的
	 */
	public static void testConcatMap() {
		List<List<String>> listArrayList = new ArrayList<>();

		List<String> stringList = new ArrayList<>();
		for (int j = 0; j < 2; j++) {
			stringList.add("A_" + j);
		}
		listArrayList.add(stringList);

		stringList = new ArrayList<>();
		for (int j = 0; j < 2; j++) {
			stringList.add("B_" + j);
		}
		listArrayList.add(stringList);
		Observable.fromIterable(listArrayList).concatMap(new Function<List<String>, ObservableSource<String>>() {
			@Override
			public ObservableSource<String> apply(List<String> stringList1) throws Exception {
				if (stringList1.get(0).startsWith("A")) {
					return Observable.fromIterable(stringList1).delay(200, TimeUnit.MILLISECONDS);
				}
				return Observable.fromIterable(stringList1);
			}
		}).subscribe(new Consumer<String>() {
			@Override
			public void accept(String s) throws Exception {
				logger.info("value is: " + s);
			}
		});

		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 从需要发送的事件当中获取指定数量的事件，并将这些事件放到缓冲区当中一并发出。
	 * buffer 有两个参数:
	 * 		参数一count用于指点缓冲区最大容量
	 * 		参数二 skip用指定当缓冲区满了时，发送下一次事件序列的时候要跳过多少元素，如果skip大小和count不一致，可能会出现重发或漏发
	 */
	public static void testBuffer() {
		 Observable.just(1, 2, 3, 4, 5, 6)
         .buffer(3, 3)
         .subscribe(new Observer<List<Integer>>() {
             @Override
             public void onSubscribe(Disposable d) {
                 logger.info("onSubscribe");
             }

             @Override
             public void onNext(List<Integer> integers) {
                 logger.info("缓冲区大小： " + integers.size());
                 for (Integer i : integers) {
                     logger.info("元素： " + i);
                 }
             }

             @Override
             public void onError(Throwable e) {
            	 logger.info("onError: " + e.getMessage());
             }

             @Override
             public void onComplete() {
            	 logger.info("onComplete");
             }
         });
	}
	
	/**
	 * 用于将数据进行分组，每个分组都会返回一个被观察者。groupBy() 方法的返回值用于指定分组名，每返回一个新值就代表会创建一个分组
	 * 事件触发顺序是按照元素的顺序，不是按照分组的顺序
	 */
	public static void testGroupBy() {
        Observable.just(1, 2, 3, 4, 5, 6, 7)
        .groupBy(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) {
                if (integer % 2 == 0) {
                    return "even";
                }
                return "odd";
            }
        })
        .subscribe(new Observer<GroupedObservable<String, Integer>>() {
            @Override
            public void onSubscribe(Disposable d) {
                logger.info("onSubscribe");
            }

            @Override
            public void onNext(final GroupedObservable<String, Integer> observable) {
                observable.subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        logger.info("GroupedObservable onSubscribe");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        logger.info("GroupedObservable onNext key :" + observable.getKey());
                        logger.info("GroupedObservable onNext value :" + integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        logger.info("GroupedObservable onError");
                    }

                    @Override
                    public void onComplete() {
                        logger.info("GroupedObservable onComplete");
                    }
                });
            }

            @Override
            public void onError(Throwable e) {
                logger.info("onError");
            }

            @Override
            public void onComplete() {
                logger.info("onComplete");
            }
        });
	}
	
	/**
	 *  scan()操作符对每一个数据项应用apply函数(前一次调用apply函数返回结果+下一个数据项)，并将返回结果应用accept函数，
	 * 	随后将返回结果和下一个数据项继续应用apply函数，并将返回结果应用accept函数，以此类推...
	 * 	注意： 当对第一个数据项应用apply函数时，由于只有一个数据项参数，所以会直接调用accept函数
	 */
	public static void testScan() {
        Observable.just(1, 5, 8, 12).scan(new BiFunction<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) {
                logger.info("integer : " + integer);
                logger.info("integer2 : " + integer2);
                return integer + integer2;
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                logger.info("accept : " + integer);
            }
        });
	}
	
	
	/**************************组合操作符*************************/

	/**
	 * 用于将多个观察者组合在一起，然后按照参数的传入顺序串行发送事件，concat() 最多只可以发送4个事件，超过4个用concatArray函数
	 */
	public static void testConcat() {
		 Observable.concat(Observable.just(1, 2),
	                Observable.just(3, 4),
	                Observable.just(5, 6),
	                Observable.just(7, 8)).subscribe(new Consumer<Integer>() {
	            @Override
	            public void accept(Integer integer) throws Exception {
	            	logger.info("accept : " + integer);
	            }
	        });
	}
	
	/**
	 * 这个方法与 concat() 作用基本一样，只是 concat() 是串行发送事件，而 merge() 并行发送事件(不保证顺序)
	 * mergeArray() 可以发送 4 个以上的被观察者
	 */
	public static void testMerge() {
		Observable.concat(Observable.interval(1, TimeUnit.SECONDS).map(new Function<Long, String>() {
			@Override
			public String apply(Long aLong) {
				return "Test_A_" + aLong;
			}
		}), Observable.interval(1, TimeUnit.SECONDS).map(new Function<Long, String>() {
			@Override
			public String apply(Long aLong) {
				return "Test_B_" + aLong;
			}
		})).subscribe(new Consumer<String>() {
			@Override
			public void accept(String s) {
				logger.info("accept: " + s);
			}
		});
		try {
			Thread.sleep(10000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 在 concatArray() 和 mergeArray() 两个方法当中，如果其中有一个被观察者发送了一个 Error 事件，那么就会停止发送事件，
	 * 如果想 onError() 事件延迟到所有被观察者都发送完事件后再执行的话(只会中断当前被观察者后续操作)，可以使用 concatArrayDelayError() 和 mergeArrayDelayError()
	 *
	 */
	@SuppressWarnings("unchecked")
	public static void testConcatArrayDelayError() {
		// 写法1
		Observable.concatArrayDelayError(Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) {
                emitter.onNext(1);
                emitter.onError(new Exception("Normal Exception"));
                emitter.onNext(2);
            }
        }), Observable.just(30, 40, 50)).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {
                logger.info("onNext: " + integer);
            }

            @Override
            public void onError(Throwable e) {
                logger.error("onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
	}

	/**
	 * zip() 操作符返回一个 Obversable，它使用这个函数按顺序结合两个或多个 Observables 发射的数据项，
	 * 然后它发射这个函数返回的结果。它按照严格的顺序应用这个函数。
	 * 它只发射与发射数据项最少的那个 Observable 一样多的数据
	 */
	public static void testZip() {
        Observable.zip(Observable.just(1, 2, 3, 4), Observable.just(5, 6, 7, 8, 9),
                new BiFunction<Integer, Integer, String>() {
                    @Override
                    public String apply(Integer integer, Integer integer2) throws Exception {
                        return String.valueOf(integer) + "_" + String.valueOf(integer2);
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        logger.info("accept: " + s);
                    }
                });
	}

	/**
	 * combineLatest() 的作用与 zip() 类似，combineLatest() 发送事件的序列是与发送的时间线有关的，
	 *      当两个 Observables 中的任何一个发射了一个数据时，通过一个指定的函数组合每个 Observable 发射的最新数据，然后发射这个函数的结果
	 * combineLatestDelayError 多了延迟发送onError() 的功能
	 */
	public static void testCombineLatest() {
		Observable.combineLatest(
	               Observable.intervalRange(1, 4, 1, 1, TimeUnit.SECONDS)
	                        .map(new Function<Long, String>() {
	                            @Override
	                            public String apply(Long aLong) {
	                                String s1 = "A" + aLong;
	                                logger.info("A 发送的事件 " + s1);
	                                return s1;
	                            }
	                        }), 
	               Observable.intervalRange(1, 4, 2, 1, TimeUnit.SECONDS)
	                        .map(new Function<Long, String>() {
	                            @Override
	                            public String apply(Long aLong) {
	                                String s1 = "B" + aLong;
	                                logger.info("B 发送的事件 " + s1);
	                                return s1;
	                            }
	                        }),
	                new BiFunction<String, String, String>() {
	                    @Override
	                    public String apply(String value1, String value2) throws Exception {
	                        return value1 + "_" + value2;
	                    }
	                })
	                .subscribe(new Consumer<String>() {
	                    @Override
	                    public void accept(String s) throws Exception {
	                    	logger.info("accept: " + s);
	                    }
	                });
		try {
			Thread.sleep(10000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 与 scan() 操作符的作用类似，也是将发送数据以一定逻辑聚合起来，区别在于 scan() 每处理一次数据就会将事件发送给观察者，而 reduce() 会将所有数据聚合在一起才会发送事件给观察者
	 * 下面实现的是一个求和功能
	 */
	public static void testReduce() {
		Observable.just(1, 3, 5, 7).reduce(new BiFunction<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) throws Exception {
                logger.info("integer1 : " + integer);
                logger.info("integer2 : " + integer2);
                return integer + integer2;
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                logger.info("accept : " + integer);
            }
        });
	}

	/**
	 * collect() 与 reduce() 类似，但它的目的是收集原始 Observable 发射的所有数据到一个可变的数据结构
	 */
	public static void testCollect() {
		Observable.just(1, 2, 3, 4)
        .collect(new Callable<ArrayList<Integer>>() {
            @Override
            public ArrayList<Integer> call() throws Exception {
                return new ArrayList<>();
            }
        }, new BiConsumer<ArrayList<Integer>, Integer>() {
            @Override
            public void accept(ArrayList<Integer> integers, Integer integer) throws Exception {
            	logger.info("accept : " + integers + ": " + integer);
                integers.add(integer);
            }
        })
        .subscribe(new Consumer<ArrayList<Integer>>() {
            @Override
            public void accept(ArrayList<Integer> integers) throws Exception {
                logger.info("accept : " + integers);
            }
        });
	}

	/**
	 * 在发送事件之前追加事件，startWith() 追加一个事件，startWithArray() 可以追加多个事件，追加的事件会先发出
	 */
	public static void testStartWith() {
        Observable.just(4, 5)
        .startWithArray(2, 3)
        .startWith(1)
        .subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                logger.info("accept : " + integer);
            }
        });
	}
	
	/**
	 * 返回被观察者发送事件的数量
	 */
	public static void testCount() {
        Observable.just(1, 2, 3)
        .count()
        .subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
            	logger.info("accept : " + aLong);
            }
        });
	}

	/**************************功能操作符*************************/
	
	/**
	 * 延迟一段事件再发送事件
	 */
	public static void testDelay() {
        Observable.just(1, 2, 3)
        .delay(3, TimeUnit.SECONDS)
        .subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer value) throws Exception {
                logger.info("accept : " + value);
            }
        });
        try {
			Thread.sleep(10000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Observable 发送一次事件之前都会回调这个方法
	 */
	public static void testDoOnEach() {
		Observable.just(1, 2, 3)
        .doOnEach(new Consumer<Notification<Integer>>() {
            @Override
            public void accept(Notification<Integer> integerNotification) throws Exception {
                logger.info("integerNotification value : " + integerNotification.getValue());
            }
        })
        .subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer value) throws Exception {
            	logger.info("accept : " + value);
            }
        });
	}
	
	/**
	 * Observable 发送 onNext() 之前都会先回调这个方法
	 */
	public static void testDoOnNext() {
		Observable.just(1, 2, 3)
        .doOnNext(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                logger.info("doOnNext accept : " + integer);
            }
        })
        .subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer value) throws Exception {
                logger.info("accept : " + value);
            }
        });
	}
	
	/**
	 * Observable 发送 onNext() 之后都会回调这个方法
	 */
	public static void testDoAfterNext() {
		Observable.just(1, 2, 3)
        .doAfterNext(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                logger.info("doOnNext accept : " + integer);
            }
        })
        .subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer value) throws Exception {
                logger.info("accept : " + value);
            }
        });
	}
	
	/**
	 * Observable 调用 onComplete() 之前都会回调这个方法
	 */
	public static void testDoOnComplete() {
		Observable.just(1, 2, 3)
        .doOnComplete(new Action() {
            @Override
            public void run() throws Exception {
                logger.info("doOnComplete run()");
            }
        })
        .subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer value) throws Exception {
            	logger.info("accept : " + value);
            }
        });
	}
	
	/**
	 * Observable 发送 onError() 之前都会回调这个方法
	 */
	public static void testOnError() {
		Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onError(new Exception("Normal Exception"));
            }
        }).doOnError(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                logger.info("doOnError accept() : " + throwable.getMessage());
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {
                logger.info("onNext : " + integer);
            }

            @Override
            public void onError(Throwable e) {
                logger.info("onError : " + e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
	}
	
	/**
	 * Observable 发送 onSubscribe() 之前会回调这个方法
	 */
	public static void testDoOnSubscribe() {
		Observable.just(1, 2, 3)
        .doOnSubscribe(new Consumer<Disposable>() {
			@Override
			public void accept(Disposable t) throws Exception {
				logger.info("doOnSubscribe");
			}
        	
		})
        .subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer value) throws Exception {
            	logger.info("accept : " + value);
            }
        });
	}
	
	/**
	 * 当调用 Disposable 的 dispose() 之后会回调该方法
	 */
	public static void testDoOnDispose() {
		Observable.just(1, 2, 3)
        .doOnDispose(new Action() {
			@Override
			public void run() throws Exception {
				logger.info("doOnDispose run");
			}
		})
        .subscribe(new Observer<Integer>() {
        	Disposable dis = null;
        	
            @Override
            public void onSubscribe(Disposable d) {
            	dis = d;
            }

            @Override
            public void onNext(Integer integer) {
                logger.info("onNext : " + integer);
                if (integer % 2 == 0) {
                	dis.dispose();
                }
            }

            @Override
            public void onError(Throwable e) {
                logger.info("onError : " + e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
	}
	
	/**
	 *      在回调 onSubscribe 之前回调该方法的第一个参数的回调方法，可以使用该回调方法决定是否取消订阅，
	 * doOnLifecycle() 第二个参数的回调方法的作用与 doOnDispose() 一样
	 */
	public static void testDoOnLifecycle() {
		Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onComplete();
            }
        }).doOnLifecycle(new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {
                logger.info("doOnLifecycle accept");
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                logger.info("doOnLifecycle run");
            }
        }).subscribe(new Observer<Integer>() {

            private Disposable disposable;

            @Override
            public void onSubscribe(Disposable d) {
                logger.info("onSubscribe");
                this.disposable = d;
            }

            @Override
            public void onNext(Integer integer) {
                logger.info("onNext : " + integer);
                disposable.dispose();
            }

            @Override
            public void onError(Throwable e) {
                logger.info("onError : " + e.getMessage());
            }

            @Override
            public void onComplete() {
                logger.info("onComplete");
            }
        });
	}
	
	/**
	 * doOnTerminate 是在 onError 或者 onComplete 发送之前回调，
	 *      而 doAfterTerminate 则是 onError 或者 onComplete 发送之后回调
	 */
	public static void testDoOnTerminate() {
		Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onComplete();
            }
        }).doOnTerminate(new Action() {
            @Override
            public void run() throws Exception {
                logger.info("doOnTerminate run");
            }
        }).doAfterTerminate(new Action() {
            @Override
            public void run() throws Exception {
                logger.info("doAfterTerminate run");
            }
        }).subscribe(new Observer<Integer>() {

            @Override
            public void onSubscribe(Disposable d) {
                logger.info("onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                logger.info("onNext : " + integer);
            }

            @Override
            public void onError(Throwable e) {
                logger.info("onError : " + e.getMessage());
            }

            @Override
            public void onComplete() {
                logger.info("onComplete");
            }
        });
	}
	
	/**
	 * 	在所有事件发送完毕之后回调该方法。 doFinally() 和 doAfterTerminate() 的区别在于取消订阅时，
	 * 	如果取消订阅，之后 doAfterTerminate() 就不会被回调，而 doFinally() 无论怎么样都会被回调，且都会在事件序列的最后
	 */
	public static void testDoFinally() {
		Observable.just(1, 2, 3)
        .doFinally(new Action() {
			@Override
			public void run() throws Exception {
				logger.info("doFinally run");
			}
		})
        .doAfterTerminate(new Action() {
            @Override
            public void run() throws Exception {
                logger.info("doAfterTerminate run");
            }
        })
        .subscribe(new Observer<Integer>() {
        	Disposable dis = null;
        	
            @Override
            public void onSubscribe(Disposable d) {
            	dis = d;
            }

            @Override
            public void onNext(Integer integer) {
                logger.info("onNext : " + integer);
                if (integer % 2 == 0) {
                	dis.dispose();
                }
            }

            @Override
            public void onError(Throwable e) {
                logger.info("onError : " + e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
	}
	
	/**
	 * 当接受到一个 onError() 事件之后回调，返回的值会回调 onNext() 方法，并正常结束该事件序
	 */
	public static void testOnErrorReturn() {
		Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onError(new Exception("Normal Exception"));
            }
        }).onErrorReturn(new Function<Throwable, Integer>() {
            @Override
            public Integer apply(Throwable throwable) throws Exception {
                return 7;
            }
        }).subscribe(new Observer<Integer>() {

            @Override
            public void onSubscribe(Disposable d) {
                logger.info("onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                logger.info("onNext : " + integer);
            }

            @Override
            public void onError(Throwable e) {
                logger.info("onError : " + e.getMessage());
            }

            @Override
            public void onComplete() {
                logger.info("onComplete");
            }
        });
	}
	
	/**
	 * 当接收到 onError() 事件时，返回一个新的 Observable，并正常结束事件序列
	 * 当接收到 onError() 事件时，被触发，subscribe方法中的onError不会再被触发
	 */
	public static void testOnErrorResumeNext() {
		Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onError(new Exception("Normal Exception"));
            }
        }).onErrorResumeNext(new Function<Throwable, ObservableSource<? extends Integer>>() {
            @Override
            public ObservableSource<? extends Integer> apply(Throwable throwable) throws Exception {
                logger.info("onErrorResumeNext apply: " + throwable.getMessage());
                return Observable.just(4, 5, 6);
            }
        }).subscribe(new Observer<Integer>() {

            @Override
            public void onSubscribe(Disposable d) {
            	logger.info("onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                logger.info("onNext : " + integer);
            }

            @Override
            public void onError(Throwable e) {
                logger.info("onError : " + e.getMessage());
            }

            @Override
            public void onComplete() {
                logger.info("onComplete");
            }
        });
	}

	/**
	 * 与 onErrorResumeNext() 作用基本一致，但是这个方法只能捕捉 Exception，不能捕获 Error
	 * 将 emitter.onError(new Exception("Normal Exception")) 改为 emitter.onError(new Error("Normal Exception"));
	 * 异常将不会被捕获
	 */
	public static void testOnExceptionResumeNext() {
		Observable.create(new ObservableOnSubscribe<Integer>() {
		    @Override
		    public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
		        emitter.onNext(1);
		        emitter.onNext(2);
		        emitter.onError(new Exception("Normal Exception"));
		        // onExceptionResumeNext不能捕获Error
//		        emitter.onError(new Error("Normal Error"));
		        
		    }
		}).onExceptionResumeNext(new Observable<Integer>() {
		    @Override
		    protected void subscribeActual(Observer<? super Integer> observer) {
		        logger.info("onExceptionResumeNext subscribeActual");
		        observer.onNext(3);
		        observer.onComplete();
		    }
		}).subscribe(new Observer<Integer>() {

		    @Override
		    public void onSubscribe(Disposable d) {
		        logger.info("onSubscribe");
		    }

		    @Override
		    public void onNext(Integer integer) {
		        logger.info("onNext : " + integer);
		    }

		    @Override
		    public void onError(Throwable e) {
		        logger.info("onError : " + e.getMessage());
		    }

		    @Override
		    public void onComplete() {
		        logger.info("onComplete");
		    }
		});
	}

	/**
	 * 如果出现错误事件，则会重新发送所有事件序列指定次数
	 */
	public static void testRetry() {
		Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onError(new Error("Normal Exception"));
                emitter.onNext(2);
            }
        }).retry(2).subscribe(new Observer<Integer>() {

            @Override
            public void onSubscribe(Disposable d) {
                logger.info("onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                logger.info("onNext : " + integer);
            }

            @Override
            public void onError(Throwable e) {
                logger.info("onError : " + e.getMessage());
            }

            @Override
            public void onComplete() {
                logger.info("onComplete");
            }
        });
	}

	private static int index = 1;
	/**
	 * 出现错误事件之后，可以通过此方法判断是否继续发送事件
	 */
	public static void testRetryUntil() {
	    Observable.create(new ObservableOnSubscribe<Integer>() {
	                @Override
	                public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
	                    emitter.onNext(1);
	                    emitter.onNext(2);
	                    emitter.onError(new Exception("Normal Exception"));
	                }
	            }).retryUntil(new BooleanSupplier() {
	                @Override
	                public boolean getAsBoolean() throws Exception {
	                    logger.info("getAsBoolean");
	                    return index == 7;
	                }
	            }).subscribe(new Observer<Integer>() {

	                @Override
	                public void onSubscribe(Disposable d) {
	                    logger.info("onSubscribe");
	                }

	                @Override
	                public void onNext(Integer integer) {
	                    logger.info("onNext : " + integer);
	                    index++;
	                }

	                @Override
	                public void onError(Throwable e) {
	                    logger.info("onError : " + e.getMessage());
	                }

	                @Override
	                public void onComplete() {
	                    logger.info("onComplete");
	                }
	            });
	}

	/**
	 * 以指定次数重复发送被观察者的事件
	 */
	public static void testRepeat() {
		Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
//                emitter.onError(new Exception("Normal Exception"));
                emitter.onComplete();
                
            }
        }).repeat(2).subscribe(new Observer<Integer>() {

            @Override
            public void onSubscribe(Disposable d) {
                logger.info("onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                logger.info("onNext : " + integer);
            }

            @Override
            public void onError(Throwable e) {
                logger.info("onError : " + e.getMessage());
            }

            @Override
            public void onComplete() {
                logger.info("onComplete");
            }
        });
	}
	
	/**
	 * 返回一个新的被观察者来决定是否重复发送事件。
	 * 如果新的被观察者返回 onComplete 或者 onError 事件，则旧的被观察者不会发送事件。
	 * 如果新的被观察者返回其他事件，则旧的观察者会发送事件
	 */
	public static void testRepeatWhen() {
		Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onComplete();
            }
        }).repeatWhen(new Function<Observable<Object>, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Observable<Object> objectObservable) throws Exception {
//                return Observable.empty();
//                return Observable.error(new Exception("Normal Exception"));
                return Observable.just(1, 2);
            }
        }).subscribe(new Observer<Integer>() {

            @Override
            public void onSubscribe(Disposable d) {
                logger.info("onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                logger.info("onNext : " + integer);
            }

            @Override
            public void onError(Throwable e) {
                logger.info("onError : " + e.getMessage());
            }

            @Override
            public void onComplete() {
                logger.info("onComplete");
            }
        });
	}

	/**
	 * subscribeOn() 用于指定被观察者的线程，要注意的时，如果多次调用此方法，只有第一次有效
	 * 
	 * observeOn() 用于指定观察者的线程，每指定一次就会生效一次
	 */
	public static void testSubscribeOn() {
		Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                logger.info("Observable Thread Name:  " + Thread.currentThread().getName());
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.newThread())
		  .observeOn(new SingleScheduler()).subscribe(new Observer<Integer>() {

            @Override
            public void onSubscribe(Disposable d) {
            	 logger.info("onSubscribe " + Thread.currentThread().getName());
            }

            @Override
            public void onNext(Integer integer) {
                logger.info("onNext : " + integer);
            }

            @Override
            public void onError(Throwable e) {
                logger.info("onError : " + e.getMessage());
            }

            @Override
            public void onComplete() {
                logger.info("onComplete");
            }
        });
		
		try {
			Thread.sleep(10000L);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}
}

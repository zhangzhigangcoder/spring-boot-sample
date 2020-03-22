package com.rxjava;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 
 * rxjava demo
 * 
 * @author zhangzhigang
 *
 */
public class rxJavaDemo {
	
	public static void main(String[] args) {
		
		// 1. 小说(被观察者)
		Observable<String> novel = Observable.create(new ObservableOnSubscribe<String>() {

			@Override
			public void subscribe(ObservableEmitter<String> emitter) throws Exception {
				emitter.onNext("连载1");
				emitter.onNext("连载2");
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
				System.out.println("onSubscribe");
			}

			@Override
			public void onNext(String t) {
				if ("cancel".equals(t)) { // 取消订阅，后面的“连载3”不会再收到
					disposable.dispose();
					return;
				}
				System.out.println("onNext: " + t);
			}

			@Override
			public void onError(Throwable e) {
				System.out.println("onError: " + e.getMessage());				
			}

			@Override
			public void onComplete() {
				System.out.println("onComplete");
			}
			
		};
		
		// 3. 读者订阅小说
		novel.subscribe(reader);
	}
	
}

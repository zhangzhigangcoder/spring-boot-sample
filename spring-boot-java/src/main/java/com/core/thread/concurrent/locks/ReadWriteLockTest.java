package com.core.thread.concurrent.locks;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.xml.crypto.Data;

/**
 * 读锁 ——> 读锁   				Y
 * 读锁 ——> 写锁 		  			N
 * 写锁 ——> 写锁 		同一线程		Y
 * 写锁 ——> 读锁		同一线程		Y 	  锁降级
 * 
 * @author zhangzhigang
 *
 */
public class ReadWriteLockTest {

	private final static ReadWriteLock lock = new ReentrantReadWriteLock();

	public static void main(String[] args) {

		try {
			lock.writeLock().lock();
		} finally {
			lock.writeLock().unlock();
		}

		// final Queue3 queue = new Queue3();
		//
		// for (int i = 0; i < 3; i++) {
		// new Thread(() -> {
		// while(true) {
		// queue.get();
		// }
		// }).start();
		//
		// new Thread(() -> {
		// while(true) {
		// queue.put(new Random().nextInt(10000));
		// }
		// }).start();
		// }
	}
}

class Queue3 {
	private Object data = null;
	private final static ReadWriteLock lock = new ReentrantReadWriteLock();

	public void get() {
		lock.readLock().lock();
		try {
			System.out.println(Thread.currentThread().getName() + " be ready to read data!");
			Thread.sleep((long) Math.random() * 10);
			System.out.println(Thread.currentThread().getName() + " have read data: " + data);

		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.readLock().unlock();
		}
	}

	public void put(Object data) {
		lock.writeLock().lock();
		try {
			System.out.println(Thread.currentThread().getName() + " be ready to write data!");
			Thread.sleep((long) Math.random() * 10);
			this.data = data;
			System.out.println(Thread.currentThread().getName() + " have write data: " + data);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.writeLock().unlock();
		}
	}
}

class CachedData {
	Object data;
	volatile boolean cacheValid;
	final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

	void processCachedData() {
		rwl.readLock().lock();
		if (!cacheValid) {
			// Must release read lock before acquiring write lock
			rwl.readLock().unlock();
			rwl.writeLock().lock();
			try {
				// Recheck state because another thread might have
				// acquired write lock and changed state before we did.
				if (!cacheValid) {
					// data = ...
					cacheValid = true;
				}
				// Downgrade by acquiring read lock before releasing write lock
				rwl.readLock().lock();
			} finally {
				rwl.writeLock().unlock(); // Unlock write, still hold read
			}
		}

		try {
			// use(data);
		} finally {
			rwl.readLock().unlock();
		}
	}
}

class RWDictionary {
	
	private final Map<String, Data> m = new TreeMap<String, Data>();
	private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	private final Lock r = rwl.readLock();
	private final Lock w = rwl.writeLock();

	public Data get(String key) {
		r.lock();
		try {
			return m.get(key);
		} finally {
			r.unlock();
		}
	}

	public String[] allKeys() {
		r.lock();
		try {
			return (String[]) m.keySet().toArray();
		} finally {
			r.unlock();
		}
	}

	public Data put(String key, Data value) {
		w.lock();
		try {
			return m.put(key, value);
		} finally {
			w.unlock();
		}
	}

	public void clear() {
		w.lock();
		try {
			m.clear();
		} finally {
			w.unlock();
		}
	}
}

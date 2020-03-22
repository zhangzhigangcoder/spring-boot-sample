package com.core.thread.concurrent.locks;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicInteger;

import sun.misc.Unsafe;

/**
 * @author zhangzhigang
 *
 */
@SuppressWarnings("restriction")
public class UnsafeTest {
	
	private static final Unsafe unsafe = getUnsafe();
	
	private static final Object obj = new AtomicInteger(10);
	
	private static final long offset = objectFieldOffsetTest();

	public static void main(String[] args) throws Exception {
		parkTest2();
	}
	
	public static final Unsafe getUnsafe() {
		try {
			 Field f = Unsafe.class.getDeclaredField("theUnsafe");
			 f.setAccessible(true);
			 return (Unsafe) f.get(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return null;
	}
	
	/**
	 * allocateInstance(Class<?> var1)
	 * 实例化对象，可以实例化私有类
	 */
	public static void allocateInstanceTest() {
		try {
//			Person person = new Person();
			Person person = (Person) unsafe.allocateInstance(Person.class);
			person.setName("haha");
			System.out.println(person.getName());
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
	}
	
	 /**
	  * objectFieldOffset(Field field)
	  * 返回指定field在对象内存中的地址偏移量，在这个类的其它方法中这个值用作访问一个特定field的一种方式，
	  * 这个值对于指定的field是惟一的，每次调用都应该返回相同的值
	  */
	 public static long objectFieldOffsetTest() {
		try {
			return unsafe.objectFieldOffset(AtomicInteger.class.getDeclaredField("value"));
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		return 0;
	 }
	
	/**
	 * compareAndSwapInt(Object obj, long offset, int expect, int update)
	 * 比较obj的offset处内存位置中的值和期望的值，如果相同则更新，此更新是不可中断的
	 * obj 需要更新的对象
	 * offset obj中整型field的偏移量
	 * expect 期望field中存在的值
	 * update 如果期望值expect与field的当前值相同，设置field的值为这个新值
	 * return 如果field的值被更改则返回true
	 * 
	 * CAS步骤：
	 * 	获取obj对象中为offset的偏移量值
	 *  比较偏移量值和expect
	 *  如果相同则更新为update，否则不更新
	 */
	 public static boolean compareAndSwapIntTest() {
		 boolean result = unsafe.compareAndSwapInt(obj, offset, 10, 12);
		 System.out.println(obj);
		 System.out.println(result);
		 return result;
	 }
	 
	 /**
	  * getAndSetInt(Object obj, long offset, int newValue)
	  * 为指定obj的指定offset的field赋值，并返回赋值成功时的前一个值
	  * @return
	  */
	 public static int getAndSetIntTest() {
		 int result = unsafe.getAndSetInt(obj, offset, 12);
		 System.out.println(result);
		 return result;
	 }
	 
	 /**
	  * getAndAddInt(Object obj, long offset, int delta)
	  * 为指定obj的指定offset的field增加delta，并返回更新成功时的前一个值
	  * @return
	  */
	 public static int getAndAddIntTest() {
		 int result = unsafe.getAndAddInt(obj, offset, 12);
		 System.out.println(obj);
		 System.out.println(result);
		 return result;
	 }
	 
	 /**
	  * getIntVolatile(Object obj, long offset)
	  * 获取obj对象中offset偏移地址对应整形field的值，支持volatile语义
	  * @return
	  */
	 public static int getIntVolatile() {
		 int result =  unsafe.getIntVolatile(obj, offset);
		 System.out.println(result);
		 return result;
	 }
	 
	 /**
	  * putIntVolatile(Object obj, long offset, int value);
	  * 为指定对象指定offset的整型field赋值，使用volatile语义，即赋值后立马更新到主内存对其它线程可见
	  */
	 public static void putIntVolatile() {
		 unsafe.putIntVolatile(obj, offset, 20);
		 System.out.println(obj);
	 }
	 
	 /**
	  * putOrderedInt(Object obj, long offset, int value);
	  * 为指定对象指定offset的整型field赋值，没有volatile语义，即赋值后不能立马更新到主内存对其它线程可见，要求field本身有volatile修饰
	  */
	 public static void putOrderedIntTest() {
		 unsafe.putOrderedInt(obj, offset, 20);
		 System.out.println(obj);
	 }
	 
	 /**
	  * arrayBaseOffset(Class arrayClass)
	  * 获取数组第一个元素的偏移地址
	  * arrayIndexScale(Class arrayClass)
	  * 获取数组中元素的增量地址
	  */
	 public static void arrayTest() {
		 String[] array1 = new String[]{"abc", "efg", "hij", "kl", "mn", "xyz"};
	        String[] array2 = new String[]{"abc1", "efg1", "hij1", "kl1", "mn1", "xyz1"};
	        Class<?> ak = String[].class;
	        int base = unsafe.arrayBaseOffset(ak);
	        int scale = unsafe.arrayIndexScale(ak);
	        int shift = 31 - Integer.numberOfLeadingZeros(scale);
	        // 获取数组中index为2的元素内存地址
//	        long offset = (2 << shift) + base;
	        long offset = 2 * scale + base;
	        String ele1 = (String) unsafe.getObject(array1, offset);
	        String ele2 = (String) unsafe.getObject(array2, offset);
	        System.out.println(base);
	        System.out.println(scale);
	        System.out.println(shift);
	        System.out.println(ele1);
	        System.out.println(ele2);
	        // 修改array1数组索引为2的元素值
	        unsafe.putObject(array1, offset, "haha");
	        ele1 = (String) unsafe.getObject(array1, offset);
	        System.out.println(ele1);
	 }
	 
	 /**
	  * park(boolean isAbsolute, long time);
	  * 若调用之前permit数量为1，则park方法直接返回，permit数量减一;反之，等待permit数量变1,线程挂起，permit数量保持不变(permit数量为0或1)
	  * isAbsolute=false 相对时间 time=ns
	  * isAbsolute=true 绝对时间(定时) time-ms
	  *  a.在调用park方法之前调用了unpark或time<0，则park直接返回，不会挂起
	  *  b.如果为调用则会挂起当前线程，挂起时间为time(time>0),如果time=0,则会一直挂起
	  *  c.在调用park方法之后调用unpark方法或所在线程被打断(调用interrupt方法)
	  *  d.park未知原因调用出错则直接返回(一般不会出现)
	  *  
	  * unpark(Thread thread)
	  * 若调用之前permit数量为0，permit数量加一(若此前调用了park方法，则线程恢复执行);
	  * 反之，permit数量保持不变(多次调用效果等同调用一次)
	  * 
	  * 1.当前线程已经调用过unpark(多次调用unpark和调用一次的效果一样),或time<0
	  */
	 public static void parkTest1() {
		 Thread curThread = Thread.currentThread();
		 unsafe.unpark(curThread);
		 unsafe.unpark(curThread);
		 unsafe.unpark(curThread);
		 unsafe.park(false, 0);
//		 unsafe.park(false, -1);
		 System.out.println("Success!");
	 }
	 /**
	  * 2.time>0,挂起时间超时
	  */
	 public static void parkTest2() {
		 // 相对时间 time=ns(1s=10^9ns)
//		 unsafe.park(false, 1000000000L);
		// 绝对时间 time=ms
		 long time = System.currentTimeMillis() + 1000;
		 unsafe.park(true, time);
		 System.out.println("Success!");
	 }
	 /**
	  * 3.在当前线程调用unpark方法或中断
	  */
	 public static void parkTest3() {
		 Thread curThread = Thread.currentThread();
		 new Thread(() -> {
			 try {
				 Thread.sleep(3000);
				 System.out.println("sleep end.");
				 curThread.interrupt();
//				unsafe.unpark(curThread);
			 } catch (InterruptedException e) {
				 e.printStackTrace();
			 }
		 }).start();
		 unsafe.park(false, 0);
		 System.out.println("Success!");
 }
}

class Person {
	private String name;
	private Person() {}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}


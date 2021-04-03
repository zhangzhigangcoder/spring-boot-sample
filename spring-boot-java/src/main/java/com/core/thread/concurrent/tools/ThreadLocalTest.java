package com.core.thread.concurrent.tools;

import lombok.Data;

import java.util.Random;

public class ThreadLocalTest {

    // 每个线程都会保存一个ThreadLocalMap对象，一个ThreadLocalMap可以保存多个ThreadLocal对象(key - value)
    private static ThreadLocal<MyThreadScopeData> nameThreadLocal = new ThreadLocal<MyThreadScopeData>() {
        @Override
        protected MyThreadScopeData initialValue() {
            return new MyThreadScopeData();
        }
    };

    private static ThreadLocal<MyThreadScopeData> ageThreadLocal = new ThreadLocal<MyThreadScopeData>() {
        @Override
        protected MyThreadScopeData initialValue() {
            return new MyThreadScopeData();
        }
    };

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int data = new Random().nextInt();
                    System.out.println(Thread.currentThread().getName() + " put name :" + data);
                    MyThreadScopeData nameInstance = nameThreadLocal.get();
                    nameInstance.setName(data + "");
                    System.out.println(Thread.currentThread().getName() + " get name: " + nameInstance.getName());

                    data = new Random().nextInt();
                    System.out.println(Thread.currentThread().getName() + " put age :" + data);
                    MyThreadScopeData ageInstance = ageThreadLocal.get();
                    ageInstance.setAge(data);
                    System.out.println(Thread.currentThread().getName() + " get age: " + ageInstance.getAge());
                }
            }).start();
        }
    }

    @Data
    static class MyThreadScopeData {
        private String name;
        private int age;
    }

}



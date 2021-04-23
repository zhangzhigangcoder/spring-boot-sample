package com.core.thread.object;

public class WaitAndNotifyDemo {

    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread(Thread.currentThread());
        synchronized (myThread) {
            try {        
                myThread.start();
                // 主线程睡眠3s
                Thread.sleep(3000);
                System.out.println("before wait");
                // 阻塞主线程
                myThread.wait();
                System.out.println("after wait");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }            
        }        
    }

    static class MyThread extends Thread {

        private Thread t;
        public MyThread(Thread t) {
            this.t = t;
        }

        public void run() {
            synchronized (this) {
                System.out.println("before notify");
                // 从wait set中任选一个等待线程，移动到entry set中，等待锁释放
                // 以线程对象作为锁对象(不推荐使用)，在线程退出后，会自动调用notifyAll()方法，所以这里的notify方法可省略
                System.out.println(t.getState());
                notify();
                System.out.println(t.getState());
                System.out.println("after notify");
            }
        }
    }

}
  
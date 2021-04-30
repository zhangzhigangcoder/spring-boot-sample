package com.core.thread.concurrent.pool;

import java.util.concurrent.*;

/**
 * 测试ExecutorCompletionService用法
 */
public class ExecutorCompletionServiceTest {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int numThread = 5;
        ExecutorService executorService = Executors.newFixedThreadPool(numThread);
        CompletionService<String> completionService = new ExecutorCompletionService<>(executorService);
        for (int i = 0; i < numThread; i++) {
            completionService.submit(new ExecutorCompletionServiceTest.Task(i));
        }
        for (int i = 0; i < numThread; i++) {
            Long beginTime = System.currentTimeMillis();
            Future<String> future = completionService.poll(-1, TimeUnit.SECONDS);
            System.out.println("future = " + future);
            // future可能为空(超时)
            if (null != future) {
                // future.get()可能为空(执行报错)
                System.out.println(future.get());
            }
            System.out.println("i=" + i + " spend time = " + (System.currentTimeMillis() - beginTime));
        }
        executorService.shutdown();
    }

    static class Task implements Callable<String> {
        private int i;

        public Task(int i) {
            this.i = i;
        }

        @Override
        public String call() throws Exception {
            Thread.sleep(10000);
            return Thread.currentThread().getName() + "执行完任务：" + i;
        }
    }
}
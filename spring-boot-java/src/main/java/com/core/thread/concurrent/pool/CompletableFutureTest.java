package com.core.thread.concurrent.pool;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @author zhangzhigang
 */
public class CompletableFutureTest {

    private static ExecutorService executor = Executors.newFixedThreadPool(3);


    public static void main(String[] args) throws Exception {
        testWhenComplete();
    }

    private static void testBase() {

        // 创建异步执行任务，有返回值
        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(CompletableFutureTest::fetchPrice, executor);

        // 没有返回值
//        CompletableFuture<Void> cf = CompletableFuture.runAsync(() -> {
//            CompletableFutureTest.fetchPrice();
//        }, executor);

        // 如果执行成功:
        cf.thenAccept((result) -> {
            System.out.println("price: " + result);
        });
        // 如果执行异常:
        cf.exceptionally((e) -> {
            e.printStackTrace();
//            return 0.0;
            return null;
        });
    }

    /**
     * 串行执行
     */
    private static void testSerial() {
        // 第一个任务
        CompletableFuture<String> cfQuery = CompletableFuture.supplyAsync(() -> {
            return queryCode("中国石油");
        }, executor);

        // cfQuery成功后继续执行下一个任务
        // 将前一个计算结果作为该任务请求参数
        CompletableFuture<Double> cfFetch = cfQuery.thenApplyAsync(code -> {
            return fetchPrice(code);
        }, executor);

        // cfFetch成功打印结果
        cfFetch.thenAccept(result -> {
            System.out.println("price: " + result);
        });
    }

    /**
     * 并行执行
     */
    private static void testParallel() {

        CompletableFuture<String> cfQueryFromSina = CompletableFuture.supplyAsync(() -> {
            return queryCode("中国石油1", "https://finance.sina.com.cn/code/");
        }, executor);

        CompletableFuture<String> cfQueryFrom163 = CompletableFuture.supplyAsync(() -> {
            return queryCode("中国石油2", "https://money.163.com/code");
        }, executor);

        // 两个任务必须全部执行完，才会触发执行下一个任务
        CompletableFuture<Void> cfQuery = CompletableFuture.allOf(cfQueryFromSina, cfQueryFrom163);

        CompletableFuture<Double> cfFetchFromSina = cfQuery.thenApplyAsync(code -> {
            return fetchPrice(null, "https://finance.sina.com/price/");
        }, executor);

        CompletableFuture<Double> cfFetchFrom163 = cfQuery.thenApplyAsync(code -> {
            return fetchPrice(null, "https://money.163.com/price/");
        }, executor);

        // 两个任务中任意一个执行完，都会触发下一个任务执行
        CompletableFuture<Object> cfFetch = CompletableFuture.anyOf(cfFetchFromSina, cfFetchFrom163);

        cfFetch.thenAccept(result -> {
            System.out.println("price: " + result);
        });
    }

    /**
     * 异步结果流水化
     */
    public static void testThenCompose() {
        CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(CompletableFutureTest::randomInteger);
        CompletableFuture<Integer> result = cf.thenCompose(i -> CompletableFuture.supplyAsync(() -> i * 10));

        try {
            System.out.println(result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 组合结果处理
     * 将两个无关的CompletableFuture组合起来，第二个Completable并不依赖第一个Completable的结果
     */
    public static void testThenCombine() {
        CompletableFuture<Integer> result = CompletableFuture.supplyAsync(CompletableFutureTest::randomInteger)
                .thenCombineAsync(CompletableFuture.supplyAsync(CompletableFutureTest::randomInteger), (i, j) -> i * j);
        try {
            System.out.println(result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 对前面计算结果进行处理，无法返回新值
     */
    public static void testWhenComplete() {
        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(CompletableFutureTest::randomInteger);
        CompletableFuture<Integer> cf2 = cf1.whenComplete((v, e) ->
                System.out.println(String.format("value:%s, exception:%s", v, e)));
        System.out.println(cf2.join());
    }

    private static int expandValue(int num) {
        return num * 10;
    }

    private static Random random = new Random();

    private static Integer randomInteger() {
        int i = random.nextInt(100);
        System.out.println("i: " + i);
        return i;
    }

    private static String queryCode(String name, String url) {
        try {
            Thread.sleep((long) (Math.random() * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("query code from " + name + "-" + url + "...");
        return "601857-" + name;
    }

    private static Double fetchPrice(String code, String url) {
        try {
            Thread.sleep((long) (Math.random() * 3000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("query price from " + code + "-" + url + "...");
        return 5 + Math.random() * 20;
    }

    private static Double fetchPrice() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
        }
//        if (0.2 < 0.3) {
//            throw new RuntimeException("fetch price failed!");
//        }
        return 5 + Math.random() * 20;
    }

    private static Double fetchPrice(String code) {
        System.out.println("fetchPrice: " + code);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
        return 5 + Math.random() * 20;
    }

    /**
     * 根据证券名称查询证券代码
     *
     * @param name
     * @return
     * @throws InterruptedException
     */
    private static String queryCode(String name) {
        System.out.println("queryCode: " + name);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "601857";
    }


}


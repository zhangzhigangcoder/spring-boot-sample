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


    public static void main(String[] args) throws Exception {
        thenComposeTest();
    }

    private static void baseUse() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // 创建异步执行任务，有返回值
        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(CompletableFutureTest::fetchPrice, executor);

        // 没有返回值
//        CompletableFuture<Void> cf = CompletableFuture.runAsync(() -> {
//            CompletableFutureTest.fetchPrice();
//        });

        // 如果执行成功:
        cf.thenAccept((result) -> {
            System.out.println("price: " + result);
        });
        System.out.println("---11---");
        // 如果执行异常:
        cf.exceptionally((e) -> {
            e.printStackTrace();
            return null;
        });
    }

    private static Double fetchPrice() {
        try {
            Thread.sleep(100);
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
     * @param name
     * @return
     * @throws InterruptedException
     */
    private static String queryCode(String name) {
        System.out.println("queryCode: " + name);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "601857";
    }

    /**
     * 串行执行
     */
    private static void test2() {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        // 第一个任务
        CompletableFuture<String> cfQuery = CompletableFuture.supplyAsync(() -> {
            return queryCode("中国石油");
        }, executor);
        // cfQuery成功后继续执行下一个任务
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
    private static void test3() {

        ExecutorService executor = Executors.newFixedThreadPool(4);

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

    public static void thenComposeTest() {
        CompletableFuture<Integer> result = CompletableFuture.supplyAsync(CompletableFutureTest::randomInteger)
                .thenCompose(i -> CompletableFuture.supplyAsync(() -> i * 10));
        try {
            System.out.println(result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static int expandValue(int num) {
        return num * 10;
    }

    /**
     * 组合处理
     */
    public static void thenCombineTest() {
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

}


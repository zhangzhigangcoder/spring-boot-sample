package com.core.thread.concurrent.pool;

import lombok.Data;

import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * @author zhangzhigang
 * https://www.jianshu.com/p/547d2d7761db
 */
public class CompletableFutureTest {

    private static ExecutorService executor = Executors.newFixedThreadPool(3);


    public static void main(String[] args) throws Exception {
//        testAllAndAny();
        Thread.sleep(100000000000L);
        System.out.println("-----");
    }

    private static void testThenAccept() {

        // 创建异步执行任务，有返回值
        CompletableFuture<CFEntity> cf = CompletableFuture.supplyAsync(CompletableFutureTest::buildEntity, executor);

        // 没有返回值
//        CompletableFuture<Void> cf = CompletableFuture.runAsync(() -> {
//            CompletableFutureTest.fetchPrice();
//        }, executor);

        // 如果执行成功:
        CompletableFuture<Void> cf2 = cf.thenAccept((result) -> {
            result.setName("李四");
            System.out.println("price: " + result);
        });
        // 如果执行异常:
        CompletableFuture<CFEntity> cf3 = cf.exceptionally((e) -> {
            e.printStackTrace();
            return null;
        });
        System.out.println(cf.join());
    }

    /**
     * 对前面计算结果进行处理，无法返回新值
     * cf1和cf2的结果一致
     */
    public static void testWhenComplete() {
        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(CompletableFutureTest::randomInteger, executor);
        CompletableFuture<Integer> cf2 = cf1.whenComplete((v, e) ->
                System.out.println(String.format("value:%s, exception:%s", v, e)));
        System.out.println(cf1.join());
        System.out.println(cf2.join());
    }

    /**
     * 与whenComplete都是对结果进行处理，区别在于：
     * whenComplete中cf1和cf2中结果一样
     * handleAsync中cf1和cf2中的结果可能不一致
     */
    public static void testHandle() {
        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(CompletableFutureTest::randomInteger, executor);
        CompletableFuture<Integer> cf2 = cf1.handleAsync((v, e) -> v + 1);
        System.out.println(cf1.join());
        System.out.println(cf2.join());
    }

    /**
     * 串行执行
     */
    private static void testThenApply() {
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
     * 串行执行，和thenApply类似，前者有返回值，后者没有返回值
     */
    private static void testThenRun() {
        // 第一个任务
        CompletableFuture<String> cfQuery = CompletableFuture.supplyAsync(() -> {
            return queryCode("中国石油");
        }, executor);

        // cfQuery成功后继续执行下一个任务
        // 将前一个计算结果作为该任务请求参数
        CompletableFuture<Void> cfFetch = cfQuery.thenRunAsync(() -> {
            System.out.println("---then Run---");
        }, executor);

        // cfFetch成功打印结果
        cfQuery.thenAccept(result -> {
            System.out.println("price: " + result);
        });
    }

    /**
     * 串行异步执行,和thenApplyAsync类似
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
     * i为第一个cf执行结果
     * j为第二个cf执行结果
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
     * 和thenCombineAsync类型，区别在于一个有返回值，一个配置
     * 用来组合两个CompletableFuture,其中一个CompletableFuture等待另一个CompletableFuture的结果
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void testThenAcceptBoth() {
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> "hello world");
        CompletableFuture<Void> cf2 = cf1.thenAcceptBoth(CompletableFuture.completedFuture("compose"),
                (x, y) -> System.out.println(x + " " + y)); // hello world compose
    }

    /**
     * Either为任意一个CompletableFuture执行完成，就会执行
     * applyToEither有返回值
     * acceptEither没有返回值
     * 超过两个CompletableFuture,用anyOf
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void testApplyToEither() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000 + random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 100;
        });

        CompletableFuture<Integer> cf2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000 + random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 200;
        });

        // 两个中任意一个计算完成,那么触发Runnable的执行
        CompletableFuture<String> cf3 = cf2.applyToEither(cf1, i -> {
            // i值为cf1和cf2中先执行完成的结果
            System.out.println("==" + i);
            return i.toString();
        });

        // 两个任意一个计算完成,那么触发Runnable的执行
        CompletableFuture<Void> f1 = cf2.acceptEither(cf1, (result) -> {
            // result值为cf1和cf2中先执行完成的结果
            System.out.println("--" + result);
        });

        System.out.println(cf3.get());
    }

    /**
     * 并行执行
     * allOf: 所有CompletableFuture执行完，才继续向下执行
     * anyOf: 任意一个CompletableFuture执行完，才继续向下执行
     */
    private static void testAllAndAny() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> cfQueryFromSina = CompletableFuture.runAsync(() -> {
//            return queryCode("中国石油1", "https://finance.sina.com.cn/code/");
            new Scanner(System.in).nextLine();
            System.out.println("-----11111111111");
        }, executor);

        CompletableFuture<Void> cfQueryFrom163 = CompletableFuture.runAsync(() -> {
//            return queryCode("中国石油2", "https://money.163.com/code");
            System.out.println("-----22222222");
        }, executor);

        List<CompletableFuture<Void>> completableFutures = Arrays.asList(cfQueryFromSina, cfQueryFrom163);

        // 两个任务必须全部执行完，才会触发执行下一个任务
        CompletableFuture<Void> cfQuery = CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture[0]));

//        cfQuery.join();
//        CompletableFuture<List<String>> result = cfQuery.thenApply(v -> completableFutures.stream()
//                .map(CompletableFuture::join)
//                .collect(Collectors.toList())
//        );
        System.out.println(cfQuery.get());
        System.out.println("-----------------");
        executor.shutdownNow();

//        CompletableFuture<Double> cfFetchFromSina = cfQuery.thenApplyAsync(code -> {
//            return fetchPrice(null, "https://finance.sina.com/price/");
//        }, executor);
//
//        CompletableFuture<Double> cfFetchFrom163 = cfQuery.thenApplyAsync(code -> {
//            return fetchPrice(null, "https://money.163.com/price/");
//        }, executor);
//
//        // 两个任务中任意一个执行完，都会触发下一个任务执行
//        CompletableFuture<Object> cfFetch = CompletableFuture.anyOf(cfFetchFromSina, cfFetchFrom163);

//        cfFetch.thenAccept(result -> {
//            System.out.println("price: " + result);
//        });
    }

//    public void testCompletableFutureForAll() {
//        List<CompletableFuture<List<Integer>>> completableFutureList = new ArrayList<>(4);
//        Instant startTime = Instant.now();
//        for (int i = 1; i <= 30; i += 10) {
//            CompletableFuture<List<Integer>> integerList = this.getIntegerList(i, 10);
//            completableFutureList.add(integerList);
//        }
//        CompletableFuture<List<List<Integer>>> sequence = this.sequence(completableFutureList);
//        CompletableFuture<List<Integer>> listCompletableFuture = sequence.thenApply(lists -> lists.stream().flatMap(Collection::stream).collect(Collectors.toList()));
//        List<Integer> join = listCompletableFuture
//                .join();
//        System.out.println(join);
//        Instant endTime = Instant.now();
//
//        System.out.println(Duration.between(startTime, endTime).getSeconds());
//    }
//
//    public <T> CompletableFuture<List<T>> sequence(Collection<CompletableFuture<T>> futureList) {
//        return CompletableFuture.allOf(futureList.toArray(new CompletableFuture<?>[0]))
//                .thenApply(v -> futureList.stream()
//                        .map(CompletableFuture::join)
//                        .collect(Collectors.toList())
//                );
//    }

    private static Random random = new Random();

    private static Integer randomInteger() {
        int i = random.nextInt(100);
        System.out.println("i: " + i);
//        int i1 = 1 / 0;
        return i;
    }

    private static String queryCode(String name, String url) {
        try {
            long start = System.currentTimeMillis();
            Thread.sleep((long) (Math.random() * 1000));
            System.out.println((System.currentTimeMillis() - start) / 1000);
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
            Thread.sleep(500000);
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

    private static CFEntity buildEntity() {
        return new CFEntity("张三");
    }

    @Data
    static class CFEntity {
        private String name;
        public CFEntity(String name) {
            this.name = name;
        }
    }

}


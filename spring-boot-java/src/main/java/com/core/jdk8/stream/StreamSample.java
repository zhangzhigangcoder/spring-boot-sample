package com.core.jdk8.stream;

import java.security.cert.PKIXRevocationChecker.Option;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import javax.tools.StandardLocation;

import org.omg.Messaging.SyncScopeHelper;

import com.core.jdk8.interfacee.Something;

@SuppressWarnings("unused")
public class StreamSample {

	public static void main(String[] args) {
		
		
//		Map<String, Integer> map = new HashMap<>();
//		map.put("1", 1);
//		map.put("1", 12);
//		map.forEach((k, v) -> System.out.println(k + ":" + v));
		
	}

	/**
	 * 创建Stream
	 * 
	 * @return
	 */
	public static void buildStream() {
		// 1.数组创建
		Stream<Integer> s3 = Stream.of(1, 2, 3, 4);
		Stream<Integer> s1 = Stream.of(new Integer[] { 1, 2, 3, 4 });
		// 没有自动封箱，不支持基本类型
		// Stream<Integer> s3 = Stream.of(new int[] {1,2,3,4});
		// IntStream.of(new int[] {1,2,3,4});

		// 2.用集合创建
		List<Integer> myList = new ArrayList<>();
		for (int i = 0; i < 100; i++)
			myList.add(i);
		// sequential stream
		Stream<Integer> sequentialStream = myList.stream();
		// parallel stream
		Stream<Integer> parallelStream = myList.parallelStream();

		// 3.Stream.generate() Stream.iterate()
		Stream<String> stream1 = Stream.generate(() -> {
			return "abc";
		});
		Stream<String> stream2 = Stream.iterate("abc", (i) -> i);

		// 4.Arrays.stream() String.chars()
		LongStream ls = Arrays.stream(new long[] { 1, 2, 3, 4 });
		IntStream ls2 = "abc".chars();
		Map<String, String> map = new HashMap<String, String>();

		// Map<Integer, TestpaperItemEntity> testpaperItemResultEntityMap =
		// testpaperItemEntities.stream()
		// .collect(Collectors.toMap(TestpaperItemEntity::getId, Function.identity(),
		// (key1, key2) -> key2));
	}

	/**
	 * 中间处理
	 */
	public static void intermediateOper() {
		// 1.Stream.filter(Predicate<? super T> predicate)
		// 过滤
		List<Integer> myList = new ArrayList<>();
		for (int i = 0; i < 100; i++)
			myList.add(i);
		Stream<Integer> sequentialStream = myList.stream();
		Stream<Integer> highNums = sequentialStream.filter(p -> p > 90);
		System.out.println("High Nums greater than 90=");
		highNums.forEach(p -> System.out.print(p + " "));

		// 2.Stream.map(Function<? super T, ? extends R> mapper)
		// 投影
		Stream<String> names = Stream.of("aBc", "d", "ef");
		// collect -> List
		System.out.println(names.map(s -> s.toUpperCase()).collect(Collectors.toList()));
		
		// collect -> Map
		List<TestEntity> list = new ArrayList<>();
		list.add(new TestEntity("1", "name1"));
		list.add(new TestEntity("1", "name11"));
		list.add(new TestEntity("2", "name22"));
		list.add(new TestEntity("2", "name2"));
		list.add(new TestEntity("3", "name3"));
		list.add(new TestEntity("3", "name33"));
		list.add(new TestEntity("4", "name4"));
		Map<String, TestEntity> map = list.stream().collect(Collectors.toMap(TestEntity::getId, Function.identity(), (k1, k2) -> k2));
		map.forEach((k, v) -> System.out.println(k + ":" + v));

		// 3.Stream.flatMap(Function<? super T, ? extends Stream<? extends R>> mapper)
		// 合并
		Stream<List<String>> namesOriginalList = Stream.of(Arrays.asList("Pankaj"), Arrays.asList("David", "Lisa"),
				Arrays.asList("Amit"));
		Stream<String> flatStream = namesOriginalList.flatMap(strList -> strList.stream());
		flatStream.forEach(System.out::println);

		// 4.Stream.sorted()
		Stream<String> names2 = Stream.of("aBc", "d", "ef", "123456");
		List<String> reverseSorted = names2.sorted(Comparator.reverseOrder()).collect(Collectors.toList());
		System.out.println(reverseSorted);

		Stream<String> names3 = Stream.of("aBc", "d", "ef", "123456");
		List<String> naturalSorted = names3.sorted().collect(Collectors.toList());
		System.out.println(naturalSorted);

		// 5.distinct()
		// 去重
		Stream<Integer> numbers8 = Stream.of(1, 1, 2, 3, 4, 5, 10).distinct();
		numbers8.forEach(System.out::println);

		// 6.limit(long maxSize)
		// 返回前几个
		Stream<Integer> numbers9 = Stream.of(1, 1, 2, 3, 4, 5, 10).limit(3);
		numbers9.forEach(System.out::println);

		// 7.skip(long n);
		// 跳过前几个
		Stream<Integer> numbers10 = Stream.of(1, 1, 2, 3, 4, 5, 10).skip(3);
		numbers10.forEach(System.out::println);

		// 8.peek
		// 不会对数据产生影响，懒执行，可以作为中间操作使用
		Stream.of("one", "two", "three", "four").peek(e -> System.out.println("Peeked value: " + e))
				.map(String::toUpperCase).peek(e -> System.out.println("Mapped value: " + e))
				.collect(Collectors.toList());
	}

	/**
	 * terminal operations
	 */
	public static void terminalOper() {
		// 1.Stream.reduce(BinaryOperator<T> accumulator)
		// 遍历运算
		Stream<Integer> numbers = Stream.of(1, 2, 3, 4, 5);
		Optional<Integer> intOptional = numbers.reduce((i, j) -> {
			return i * j;
		});
		if (intOptional.isPresent())
			System.out.println("Multiplication = " + intOptional.get());

		// reduce(T identity, BinaryOperator<T> accumulator)
		// 设置初始值
		Stream<Integer> numbers2 = Stream.of(1, 2, 3, 4, 5);
		Integer intOptional2 = numbers.reduce(2, (i, j) -> {
			return i * j;
		});
		System.out.println(intOptional2);

		// 2.Stream.count()
		// 个数统计
		Stream<Integer> numbers3 = Stream.of(1, 2, 3, 4, 5);
		System.out.println("Number of elements in stream=" + numbers3.count());

		// 3.Stream.forEach(Consumer<? super T> action)
		// 遍历
		Stream<Integer> numbers4 = Stream.of(1, 2, 3, 4, 5);
		numbers4.forEach(i -> System.out.println(i + ","));

		// 4.Stream.match()
		// 匹配
		Stream<Integer> numbers5 = Stream.of(1, 2, 3, 4, 5);
		System.out.println("Stream contains 4: " + numbers5.anyMatch(i -> i == 4));

		Stream<Integer> numbers6 = Stream.of(1, 2, 3, 4, 5, 11);
		System.out.println("Stream contains all elements less than 10: " + numbers6.allMatch(i -> i < 10));

		Stream<Integer> numbers7 = Stream.of(1, 2, 3, 4, 5, 10);
		System.out.println("Stream doesn`t contain 10: " + numbers7.noneMatch(i -> i == 10));

		// 5.findFirst() findAny()
		// 串行：findAny 等同于 findFirst，都是返回第一个元素
		// 并行：findAny 返回最先返回的元素， findFirst返回第一个元素
		List<String> strs = Arrays.asList("d", "b", "a", "c", "a");
		Optional<String> first = strs.parallelStream().filter(str -> !str.equals("a")).findFirst();
		Optional<String> any = strs.parallelStream().filter(str -> !str.equals("a")).findAny();
		if (first.isPresent())
			System.out.println(first.get());
		if (any.isPresent())
			System.out.println(any.get());

		// 6.min() max()
		Optional<String> min = strs.stream().min((o1, o2) -> o1.compareTo(o2));
		Optional<String> max = strs.stream().max((o1, o2) -> o1.compareTo(o2));
		System.out.println(String.format("min:%s; max:%s", min.get(), max.get()));// min:a; max:d
	}

	/**
	 * 1.If you are using parallel stream and lambda expressions are stateful, it
	 * can result in random responses 2.Once a Stream is consumed, it can’t be used
	 * later on. As you can see in above examples that every time I am creating a
	 * stream.
	 */
	public static void statefulParallelStream() {
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
		List<Integer> result = new ArrayList<>();
		Stream<Integer> stream = list.parallelStream();
		stream.map(s -> {
			synchronized (result) {
				if (result.size() < 10) {
					result.add(s);
				}
				return s;
			}
		}).forEach(e -> {
		});
		System.out.println(result);
	}

	public static int sumStream(List<Integer> list) {
		return list.stream().filter(i -> i > 10).mapToInt(i -> i).sum();
	}

	public static List<Integer> buildList() {
		List<Integer> list = new ArrayList<>();
		for (int i = 1; i < 10; i++) {
			int ii = new Random().nextInt(20);
			if (ii > 10)
				System.out.println("ii== " + ii);
			list.add(ii);
		}
		return list;
	}
	
	static class  TestEntity {
		private String id;
		private String name;
		
		public TestEntity(String id, String name) {
			this.id = id;
			this.name = name;
		}
		public String getId() {
			return this.id;
		}
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("TestEntity [id=");
			builder.append(id);
			builder.append(", name=");
			builder.append(name);
			builder.append("]");
			return builder.toString();
		}
		
	}
	
}

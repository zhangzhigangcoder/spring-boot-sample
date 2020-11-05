package com.core.jdk8.optional;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

@SuppressWarnings("unused")
public class OptionalTest {

	public static void main(String[] args) {
//		Optional<String> s = Optional.ofNullable("111");
//		if (s.isPresent()) {
//			System.out.println(s.get());
//		}
		String id = "蓝苏E36K29";
		System.out.println(id.substring(0,1) + "---" + id.substring(1));
	}
}

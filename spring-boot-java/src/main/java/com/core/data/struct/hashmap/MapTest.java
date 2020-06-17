package com.core.data.struct.hashmap;

import com.core.copy.Person;

public class MapTest {

	public static void main(String[] args) {
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("1", "2");
////		System.out.println(map.get("1"));
//		map.entrySet().forEach(p -> System.out.println(p.getKey() + "-" + p.getValue()));
		Person p = new Person("111", 222);
		System.out.println(p.hashCode());
		System.out.println(System.identityHashCode(p));
	}
}

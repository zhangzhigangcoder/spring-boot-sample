package com.core.collection;


import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapTest {
    public static void main(String[] args) {
        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("b", 2);
        map.put("c", 3);
        map.put("a", 1);
        map.put("d", 4);
        // 可以保证遍历顺序为插入顺序
        for (Map.Entry entry : map.entrySet()) {
            System.out.println(entry.getKey() + "-"  + entry.getValue());
        }
    }
}

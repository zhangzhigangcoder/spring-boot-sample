package com.core.collection.map;


import java.util.HashMap;
import java.util.Map;


public class HashMapTest {

    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("b", 2);
        map.put("c", 3);
        map.put("a", 1);
        map.put("d", 4);
        // 保证不了遍历顺序为插入顺序
        for (Map.Entry entry : map.entrySet()) {
            System.out.println(entry.getKey() + "-"  + entry.getValue());
        }
    }
}

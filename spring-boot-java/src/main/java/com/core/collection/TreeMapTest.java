package com.core.collection;


import java.util.Comparator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class TreeMapTest {
    public static void main(String[] args) {

        TreeMap<String, String> map = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        map.put("1", "1");
        map.put("2", "2");
        map.put("3", "3");
        map.put("4", "4");
        map.put("5", "5");

        // 返回小于等于指定key
        // 1, 2, 3
         SortedMap<String, String> sortedMap = map.headMap("3", true);

        // 返回大于等于指定key
        // 3, 4, 5
        // SortedMap<String, String> sortedMap = map.tailMap("3", true);

        // 返回指定子map
        // 2, 3, 4
        // SortedMap<String, String> sortedMap = map.subMap("2", true, "4", true);

        // 返回最接近的大于等于key的键
        // String key = map.ceilingKey("2");

        // 返回最接近的小于等于key的键
        // String key = map.floorKey("3");

        // 返回最接近的大于key的键
        // String key = map.higherKey("4");

        // 返回最接近的小于key的键
        // String key = map.lowerKey("4");

        // 移除并返回第一个元素
        // Map.Entry<String, String> firstEntry = map.pollFirstEntry();

        // 移除并返回最后一个元素
//         Map.Entry<String, String> lastEntry = map.pollLastEntry();

        sortedMap.forEach((k, v) -> System.out.println(k + "-" + v));

//        map.lowerEntry("a");







    }
}

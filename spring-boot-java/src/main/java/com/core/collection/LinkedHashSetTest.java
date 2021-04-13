package com.core.collection;


import java.util.LinkedHashSet;
import java.util.Set;


public class LinkedHashSetTest {

    public static void main(String[] args) {
        Set<String> set = new LinkedHashSet<>();
        set.add("b");
        set.add("c");
        set.add("a");
        set.add("d");
        // 可以保证插入顺序
        System.out.println(set);
    }
}

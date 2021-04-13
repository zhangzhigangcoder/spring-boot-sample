package com.core.collection;


import java.util.HashSet;
import java.util.Set;


public class HashSetTest {

    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        set.add("b");
        set.add("c");
        set.add("a");
        set.add("d");
        // 不能保证顺序
        System.out.println(set);
    }
}

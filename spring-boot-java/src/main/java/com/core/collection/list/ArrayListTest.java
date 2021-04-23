package com.core.collection.list;


import com.core.copy.Person;

import java.util.ArrayList;
import java.util.List;

public class ArrayListTest {
    public static void main(String[] args) {
        List<Person> list = new ArrayList<>();
        Person p = new Person("zhang", 11);
        p.setName("zhang");
        list.add(p);
    }
}

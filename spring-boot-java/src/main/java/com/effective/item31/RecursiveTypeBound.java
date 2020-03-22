package com.effective.item31;
import java.util.Arrays;
import java.util.List;

// Using a recursive type bound with wildcards (Page 143)
public class RecursiveTypeBound {
    public static <E extends Comparable<? super E>> E max(
        List<? extends E> list) {
        if (list.isEmpty())
            throw new IllegalArgumentException("Empty list");

        E result = null;
        for (E e : list)
            if (result == null || e.compareTo(result) > 0)
                result = e;

        return result;
    }
    
	// 遵循PECS原则，此处Comparable也是Consumer
//    public static <E extends Comparable<E>> E max2(
//            List<E> list) {
//            if (list.isEmpty())
//                throw new IllegalArgumentException("Empty list");
//
//            E result = null;
//            for (E e : list)
//                if (result == null || e.compareTo(result) > 0)
//                    result = e;
//
//            return result;
//        }

    public static void main(String[] args) {
        List<String> argList = Arrays.asList("123");
        System.out.println(max(argList));
        
        
//        List<Sub> list = new ArrayList<>();
//        list.add(new Sub("123"));
//        list.add(new Sub("234"));
//        //  不能用max2()
//        System.out.println(max2(list).getS());
    }
    
}
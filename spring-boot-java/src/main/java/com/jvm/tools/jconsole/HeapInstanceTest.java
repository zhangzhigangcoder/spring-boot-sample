package com.jvm.tools.jconsole;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * -Xms600m -Xmx600m -XX:SurvivorRatio=8
 *
 * @author zhangzhigang
 * @date 2023-01-08 20:21
 */
public class HeapInstanceTest {
    byte[] buffer = new byte[new Random().nextInt(1024 * 10)];

    public static void main(String[] args) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<HeapInstanceTest> list = new ArrayList<>();
        while (true) {
            list.add(new HeapInstanceTest());
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

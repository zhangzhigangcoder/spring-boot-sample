package com.jvm.tools.jstat;

import java.util.ArrayList;
import java.util.List;

/**
 * -Xmx60m -Xms60m -XX:SurvivorRatio=8
 *
 * @author zhangzhigang
 * @date 2023-01-08 20:21
 */
public class GCTest {

    public static void main(String[] args) {
        List<byte[]> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            byte[] arr = new byte[1024 * 100]; // 100KB
            list.add(arr);
            try {
                Thread.sleep(120);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

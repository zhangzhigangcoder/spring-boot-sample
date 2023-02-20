package com.jvm.tools.mat;

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
public class OOMTest {
    public static void main(String[] args) {
        List<Picture> list = new ArrayList<>();
        while (true) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list.add(new Picture(new Random().nextInt(100 * 50)));
        }
    }
}

class Picture {
    private byte[] pixels;

    public Picture(int length) {
        this.pixels = new byte[length];
    }
}

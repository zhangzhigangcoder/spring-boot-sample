package com.agent;

import java.lang.instrument.Instrumentation;
import java.util.Arrays;

/**
 * @Desc 测试java agent
 *      参考文档：
 *              https://www.jianshu.com/p/63c328ca208d
 *              https://www.cnblogs.com/rickiyang/p/11368932.html
 * @Author zhangzhigang
 * @Data 2020-11-28 22:56
 */
public class TestAgent {

    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("premain start");
        System.out.println(agentArgs);
    }

    public static void main(String[] args) {
        System.out.println("main");
        System.out.println(Arrays.toString(args));
    }
}

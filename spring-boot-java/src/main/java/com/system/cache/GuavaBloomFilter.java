package com.system.cache;

import java.nio.charset.Charset;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
 * Bloom过滤器处理缓存穿透问题
 * 
 *
 */
public class GuavaBloomFilter {
	public static void main(String[] args) {
        BloomFilter<String> filter = BloomFilter.create(Funnels.stringFunnel(Charset.forName("UTF-8")), 1000);
        filter.put("www.baidu.com");
        filter.put("www.163.com");
        System.out.println(filter.mightContain("www.126.com"));
        System.out.println(filter.mightContain("www.163.com"));
        System.out.println(filter.mightContain("www.baidu.com"));
    }
}
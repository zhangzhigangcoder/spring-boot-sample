package com.core.algorithm.common.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 贪婪算法-解决电台覆盖地区问题
 */
public class Greedy {

	public static void main(String[] args) {
		// 创建广播电台和覆盖地区映射关系
		@SuppressWarnings("serial")
		HashMap<String, Set<String>> broadcasts = new HashMap<String, Set<String>>() {
			{
				HashSet<String> set1 = new HashSet<>();
				set1.add("北京");
				set1.add("上海");
				set1.add("天津");
				put("k1", set1);

				HashSet<String> set2 = new HashSet<>();
				set2.add("广州");
				set2.add("北京");
				set2.add("深圳");
				put("k2", set2);

				HashSet<String> set3 = new HashSet<>();
				set3.add("成都");
				set3.add("上海");
				set3.add("杭州");
				put("k3", set3);

				HashSet<String> set4 = new HashSet<>();
				set4.add("上海");
				set4.add("天津");
				put("k4", set4);

				HashSet<String> set5 = new HashSet<>();
				set5.add("杭州");
				set5.add("大连");
				put("k5", set5);
			}
		};

		// 所有地区，每遍历一次，集合就会变小
		Set<String> allAreas = new HashSet<>();
		broadcasts.forEach((k, v) -> {
			v.forEach(p -> {
				allAreas.add(p);
			});
		});

		// 存放选择的电台
		List<String> selects = new ArrayList<>();

		Set<String> tempSet = new HashSet<>();
		String maxKey = null; // 最大有效覆盖地区的key
		int tempSize = 0; // maxKey对应的有效覆盖地区的数量
		
		while (allAreas.size() > 0) {
			maxKey = null;
			tempSize = 0;
			for (String key : broadcasts.keySet()) {
				tempSet.clear();
				tempSet.addAll(broadcasts.get(key));
				// 和剩下的取交集
				tempSet.retainAll(allAreas);
				
				// 核心
				// 每一次for循环遍历都选择有效覆盖地区最多的key
				if (tempSet.size() > 0 && (null == maxKey || tempSet.size() > tempSize)) {
					// 记录更大的key和对应的交集大小
					maxKey = key;
					tempSize = tempSet.size();
				}
			}
				
			if (null != maxKey) {
				// 记录最大key
				selects.add(maxKey);
				// 移除被选择的电台
				broadcasts.remove(maxKey);
				// 移除对应覆盖地区
				allAreas.removeAll(broadcasts.get(maxKey));
			}
		}
		System.out.println(selects);
	}
}
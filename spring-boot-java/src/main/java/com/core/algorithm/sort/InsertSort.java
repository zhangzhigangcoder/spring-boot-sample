package com.core.algorithm.sort;

import java.util.Arrays;

/**
 * 插入排序 
 * 时间复杂度为 O(n^2)
 *80000个数据执行时间：1915ms
 */
public class InsertSort {

	public static void main(String[] args) {
		 int[] arr = {101, 34, 119, 1};
//		int[] arr = new int[80000];
//		for (int i = 0; i < 80000; i++) {
//			arr[i] = (int) (Math.random() * 800_0000);
//		}
//		long start = System.currentTimeMillis();
		sort(arr);
//		System.out.println(System.currentTimeMillis() - start);
	}

	public static void sort(int[] arr) {
		for (int i = 1; i < arr.length; i++) {
			// 假设排序过后，当前位置元素不变(插入位置就是当前位置)
			int insertVal = arr[i], insertIndex = i;
			// 注意这里的循环边界, 有序数据逐次移动
			for (int j = i - 1; j >= 0 && insertVal < arr[j]; j--) {
				arr[j + 1] = arr[j];
				insertIndex--;
			}
			arr[insertIndex] = insertVal;
		}
		System.out.println("排序结果：" + Arrays.toString(arr));
	}

}
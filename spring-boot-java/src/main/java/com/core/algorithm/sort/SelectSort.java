package com.core.algorithm.sort;

import java.util.Arrays;

/**
 * 冒泡排序 时间复杂度为 O(n^2)
 *
 */
public class SelectSort {

	// 待排序数据
	private int[] arr;

	public SelectSort(int[] arr) {
		this.arr = arr;
	}

	public static void main(String[] args) {
//		int[] arr = { 34, 119, 101, 1};
		int[] arr = new int[80000];
		for (int i = 0; i < 80000; i++) {
			arr[i] = (int) (Math.random() * 800_0000);
		}
		long start = System.currentTimeMillis();
		SelectSort bubbleSort = new SelectSort(arr);
		bubbleSort.upgradeVersion();
		System.out.println(System.currentTimeMillis() - start);
	}

	/**
	 * 基础版本
	 * 注意循环的边界条件
	 */
	public void upgradeVersion() {
		for (int i = 0; i < arr.length - 1; i++) {
			int min = arr[i], minIndex = i;
			for (int j = i + 1; j < arr.length; j++) {
				if (min > arr[j]) {
					// 注意这里不交换数组元素
					min = arr[j];
					minIndex = j;
				}
			}
			// 注意每轮循环中最多只会有一次数组元素交换
			if (minIndex != i) {
				arr[minIndex] = arr[i];
				arr[i] = min;
			}
//			System.out.println("第" + (i + 1) + "次排序: " + Arrays.toString(arr));
		}
	}
}
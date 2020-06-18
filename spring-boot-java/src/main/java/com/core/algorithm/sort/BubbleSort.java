package com.core.algorithm.sort;

import java.util.Arrays;

/**
 * 冒泡排序 
 * 时间复杂度为 O(n^2)
 *80000个数据执行时间：8269ms
 */
public class BubbleSort {

	public static void main(String[] args) {
//		int[] arr = { 3, 10, -1, 20, 2 };
		int[] arr = new int[80000];
		for (int i = 0; i < 80000; i++) {
			arr[i] = (int) (Math.random() * 800_0000);
		}
		long start = System.currentTimeMillis();
		upgradeSort(arr);
		System.out.println(System.currentTimeMillis() - start);
	}

	/**
	 * 基础版本
	 */
	public static void baseSort(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			// 注意循环边界
			for (int j = 0; j < arr.length - i - 1; j++) {
				if (arr[j] > arr[j + 1]) {
					int a = arr[j + 1];
					arr[j + 1] = arr[j];
					arr[j] = a;
				}
			}
			System.out.println("第" + (i + 1) + "次排序: " + Arrays.toString(arr));
		}
	}

	/**
	 * 升级版本 最后再次检测到没有交换过数据，就会直接退出循环
	 */
	public static void upgradeSort(int[] arr) {
		// 是否进行交换
		boolean exchange = false;
		for (int i = 0; i < arr.length - 1; i++) {
			// 注意循环边界
			for (int j = 0; j < arr.length - i - 1; j++) {
				if (arr[j] > arr[j + 1]) {
					exchange = true;
					int a = arr[j + 1];
					arr[j + 1] = arr[j];
					arr[j] = a;
				}
			}
			if (!exchange) {
				break;
			}
			exchange = false;
			System.out.println("第" + (i + 1) + "次排序: " + Arrays.toString(arr));
		}
	}
}
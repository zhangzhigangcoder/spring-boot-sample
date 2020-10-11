package com.algorithm.sort;

import java.util.Arrays;

/**
 * 希尔排序 
 * 平均 时间复杂度为 O(nlog2_n) 
 * 最坏时间复杂度为O(n^s) 1<s<2 s是分组组数
 * 
 * 80000个数据执行时间：12ms
 * 800000个数据执行时间：130-150ms
 * 8000000个数据执行时间：1700ms
 */
public class ShellSort {

	public static void main(String[] args) {
//		 int[] arr = {8, 9, 1, 7, 2, 3,5, 4, 6, 0};
		int[] arr = new int[8000000];
		for (int i = 0; i < 8000000; i++) {
			arr[i] = (int) (Math.random() * 8000_0000);
		}
		long start = System.currentTimeMillis();
		shift(arr);
		System.out.println(System.currentTimeMillis() - start);
	}

	/**
	 * 交换法
	 * 
	 * @param arr
	 */
	public static void exchange(int[] arr) {
		int temp = 0;
		// 步长遍历
		for (int step = arr.length / 2; step > 0; step /= 2) {
			// 固定步长遍历
			for (int i = step; i < arr.length; i++) {
				// 从右向左依次比较交换, 由于前面都是有序数组，找到第一个大的元素，后面就不用比较了
				for (int j = i - step; j >= 0 && arr[j] > arr[j + step]; j -= step) {
					temp = arr[j];
					arr[j] = arr[j + step];
					arr[j + step] = temp;
				}
			}
		}
		System.out.println(Arrays.toString(arr));
	}

	/**
	 * 移位法
	 * 
	 * @param arr
	 */
	public static void shift(int[] arr) {
		int insertIndex = 0, insertVal = 0;
		// 步长遍历
		for (int step = arr.length / 2; step > 0; step /= 2) {
			// 固定步长遍历
			for (int i = step; i < arr.length; i++) {
				insertVal = arr[i];
				// 假设排序过后，当前位置元素不变(插入位置就是当前位置)
				insertIndex = i;
				// 从右向左依次比较交换, 由于前面都是有序数组，找到第一个大的元素，后面就不用比较了
				for (int j = i - step; j >= 0 && insertVal < arr[j]; j -= step) {
					arr[j + step] = arr[j];
					insertIndex -= step;
				}
				arr[insertIndex] = insertVal;
			}
		}
//		System.out.println(Arrays.toString(arr));
	}
}
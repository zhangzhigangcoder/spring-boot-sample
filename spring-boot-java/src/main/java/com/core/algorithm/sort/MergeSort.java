package com.core.algorithm.sort;

import java.util.Arrays;

/**
 * 归并排序 
 * 	平均 时间复杂度为 O(nlog2_n) 
 * 	最坏时间复杂度为O(n^2)
 * 
 * 80000个数据执行时间：12-22ms
 * 800000个数据执行时间：80-90ms
 * 800000个数据执行时间：900ms
 */
public class MergeSort {

	public static void main(String[] args) {
		
		int[] arr = {11, 12, 10, 3, 4}; 
		int[] temp = new int[arr.length];
//		int[] arr = new int[8000000];
//		for (int i = 0; i < 8000000; i++) {
//			arr[i] = (int) (Math.random() * 8000_0000);
//		}
//		long start = System.currentTimeMillis();
		sort(arr, 0, arr.length - 1, temp);
//		System.out.println(System.currentTimeMillis() - start);
		System.out.println("排序结果：" + Arrays.toString(arr));
	}

	public static void sort(int[] arr, int left, int right, int[] temp) {
		if (left < right) {
			int mid = (left + right) / 2;
			System.out.println("left-mid-right:" + left + "-" + mid + "-" + right);
			// 向左递归进行分解
			sort(arr, left, mid, temp);
			// 向右递归分解
			sort(arr, mid + 1, right, temp);
			// 合并
			merge(arr, left, mid, right, temp);
		}
	}
	
	/**
	 * 
	 * @param arr 待合并的原始数组
	 * @param left 左边有序数组的初始索引
	 * @param mid 中间索引
	 * @param right 右边有序数组的初始索引
	 * @param temp 临时数组
	 */
	public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
		int i = left; // 左边有序序列的初始索引
		int j = mid + 1; // 右边有序序列的初始索引
		int t = 0; // 临时数组的当前索引
		
		// 先把左右两边(有序)数组的数据有序填充到临时数组中
		// 直到左右两个数组中，有一个处理完毕
		while (i <= mid && j <= right) {
			if (arr[i] <= arr[j]) {
				temp[t] = arr[i];
				i++;
			} else {
				temp[t] = arr[j];
				j++;
			}
			t++;
		}
		
		// 把有剩余的数组数据有序的全部添加到临时数组中
		while (i <= mid) {
			temp[t] = arr[i];
			t++;
			i++;
		}
		while (j <= right) {
			temp[t] = arr[j];
			t++;
			j++;
		}
		
		// 将temp数组的元素拷贝到arr
		t = 0;
		int tempLeft = left;
		while (tempLeft <= right) {
			arr[tempLeft] = temp[t];
			t++;
			tempLeft++;
		}
		
	}
}
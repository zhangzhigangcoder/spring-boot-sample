package com.algorithm.sort;

import java.util.Arrays;

/**
 * 快速排序 
 * 	平均 时间复杂度为 O(nlog2_n) 
 * 	最坏时间复杂度为O(n^2)
 * 
 * 8_0000个数据执行时间：12-22ms
 * 80_0000个数据执行时间：80-90ms
 * 800_0000个数据执行时间：900ms
 */
public class QuickSort {

	public static void main(String[] args) {
		
		// [11, 12, 10, 3, 4] -> {4, 12, 10, 3, 11} -> {4, 3, 10, 12, 11}
		// [1, 11, 10, 3, 4] -> [1, 4, 10, 3, 11] -> [1, 4, 3, 10, 11]
		// {1, 2, 10, 11, 4} -> {1, 2, 4, 11, 10} -> {1, 2, 4, 10, 11}
//		 int[] arr = {11, 12, 10, 3, 4}; 
		int[] arr = new int[8000000];
		for (int i = 0; i < 8000000; i++) {
			arr[i] = (int) (Math.random() * 8000_0000);
		}
		long start = System.currentTimeMillis();
		sort(arr, 0, arr.length - 1);
		System.out.println(System.currentTimeMillis() - start);
		 System.out.println("排序结果：" + Arrays.toString(arr));
	}

	public static void sort(int[] arr, int left, int right) {
		int l = left, r = right, pivot = arr[(left + right) / 2], temp = 0;
		while (l < r) {
			// 查找左半边，从左向右查找
			// 直到找到值大于等于pivot的下标(最大是pivot的下标)
			while (arr[l] < pivot) { 
				l += 1;			    
			}
			// 查找右半边，从左向右查找
			// 直到找到值小于等于pivot的下标(最小是pivot的下标)
			while (arr[r] > pivot) { 
				r -= 1;	
			}
			// 如果l >=r 说明pivot左右两边都已经拍好序了(左边全部小于等于pivot,右边全部大于等于pivot)
			if (l >= r) { 
				break;
			}
			
			temp = arr[l];
			arr[l] = arr[r];
			arr[r] = temp; 
			// 如果交换完后，arr[l] == pivot, 则r--
			if (arr[l] == pivot) {
				r -= 1;
			}
			// 如果交换完后，arr[r] == pivot, 则l++
			if (arr[r] == pivot) {
				l += 1;
			}
		}

		// 如果 l == r, 必须l++,r--,否则会出现栈溢出
		if (l == r) {
			l += 1;
			r -= 1;
		}

		// 向左递归
		if (left < r) {
			sort(arr, left, r);
		}
		// 向右递归
		if (right > l) {
			sort(arr, l, right);
		}
	}
}
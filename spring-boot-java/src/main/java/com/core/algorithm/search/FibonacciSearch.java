package com.core.algorithm.search;

import java.util.Arrays;

/**
 * 斐波那契(黃金分割查找)查找
 */
public class FibonacciSearch {
	
	private static int maxSize = 20;

	public static void main(String[] args) {
		int[] arr = { -1, 2, 3, 7, 8, 9, 9, 10, 20};
		int index = search(arr, 8);
		System.out.println(index);
	}

	/**
	 * 查找一个下标
	 * @param arr
	 * @param value 要查找的值
	 * @return 下标 -1-没有找到
	 */
	public static int search(int[] arr, int value) {
		int low = 0, high = arr.length - 1, k = 0, mid = 0;
		int[] f = fib();
		while (high > f[k] -1) { // k 6
			k++;
		}
		int[] temp = Arrays.copyOf(arr, f[k]);
		for (int i = high + 1; i < temp.length; i++) {
			temp[i] = arr[high];
		}
		while (low <= high) {
			mid = low + f[k -1] -1; // 不包含黄金分割点
			if (value < temp[mid]) { 
				// 以mid - 1为上限，查找下一个黄金分割点
				high = mid - 1; 
				// => 8(f[k-1]) = 5(f[k-2]) + 3(f(k-3))
				k--; 
			} else if (value > temp[mid]) {
				// 以mid + 1为下限，查找下一个黄金分割点
				low = mid + 1;
				// 13(f[k]) = 8(f[k-1]) + 5(f[k-2]) 
				// => 对5进行黄金分割  5 = 3(f[k-3]) + 2(f[k-4])  
				// => 11 = 8(f[k-1]) + 3(f(k-3))
				k -= 2; 
			} else {
				// 由于数组高位可能是填充而来的，所以最多返回high
				if (mid <= high) {
					return mid;
				} else {
					return high;
				}
			}
		}
		return -1;
	}


	/**
	 * 构建一个斐波那契数组
	 * @return
	 */
	public static int[] fib() {
		int[] f = new int[maxSize];
		f[0] = 1;
		f[1] = 1;
		for (int i = 2; i < maxSize; i++) {
			f[i] = f[i-1] + f[i-2];
		}
		return f;
	}
}
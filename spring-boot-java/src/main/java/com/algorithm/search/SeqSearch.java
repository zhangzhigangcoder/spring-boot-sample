package com.algorithm.search;

/**
 * 线性查找
 */
public class SeqSearch {

	public static void main(String[] args) {
		int[] arr = { 3, 10, -1, 20, 2 };//
		int index = search(arr, 2);
		System.out.println(index);
	}

	/**
	 * 
	 * @param arr
	 * @param value 要查找的值
	 * @return 下标
	 */
	public static int search(int[] arr, int value) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == value) {
				return i;
			}
		}
		return -1;
	}
}
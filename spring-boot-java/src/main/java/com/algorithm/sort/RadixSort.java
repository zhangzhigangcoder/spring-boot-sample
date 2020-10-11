package com.algorithm.sort;

import java.util.Arrays;

/**
 * 基数排序 
 * 时间复杂度为 O(logR_B) B:0~9 R: 个十百
 *80000个数据执行时间：45ms
 *80_0000个数据执行时间：120ms
 *800_0000个数据执行时间：600ms
 */
public class RadixSort {

	public static void main(String[] args) {
//		int[] arr = { 3, 10, 1, 20, 2 };
		int[] arr = new int[800_0000];
		for (int i = 0; i < 800_0000; i++) {
			arr[i] = (int) (Math.random() * 80000_0000);
		}
		long start = System.currentTimeMillis();
		sort(arr);
		System.out.println(System.currentTimeMillis() - start);
	}

	/**
	 * 基础版本
	 */
	public static void sort(int[] arr) {
		int max = arr[0];
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] > max) {
				max = arr[i];
			}
		}
		int maxLength = (max + "").length();
		int[][] bucket = new int[10][arr.length];
		int[] bucketCounts = new int[10];
		for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
			for (int j = 0; j < arr.length; j++) {
				int digitOfElement = Math.abs(arr[j]) / n % 10;
				bucket[digitOfElement][bucketCounts[digitOfElement]] = arr[j];
				bucketCounts[digitOfElement]++;
			}
			int index = 0;
			for (int k = 0; k < bucketCounts.length; k++) {
				if (bucketCounts[k] > 0) {
					for (int z = 0; z < bucketCounts[k]; z++) {
						arr[index++] = bucket[k][z];
					}
				}
				bucketCounts[k] = 0;
			}
		}
		System.out.println("排序结果：" + Arrays.toString(arr));
	}
}
package com.core.algorithm.search;

import java.util.ArrayList;
import java.util.List;

/**
 * 二分法查找
 */
public class BinarySearch {

	public static void main(String[] args) {
		int[] arr = { -1, 2, 3, 7, 8, 9, 9, 10, 20 };
		int index = search(arr, 0, arr.length - 1, 20);
		System.out.println(index);
	}

	/**
	 * 查找一个下标
	 * @param arr
	 * @param value 要查找的值
	 * @return 下标 -1-没有找到
	 */
	public static int search(int[] arr, int left, int right, int value) {
		// 注意这里的退出条件
		if (left > right || value < arr[left] || value > arr[right]) {
			return -1;
		}
		int mid = (left + right) / 2;
		int midVal = arr[mid];
		if (value > midVal) { // 向右递归
			return search(arr, mid + 1, right, value);
		} else if (value < midVal) { // 向左递归
			return search(arr, left, mid - 1, value);
		} else {
			return mid;
		}
	}

	/**
	 * 查找所有下标
	 * @param arr
	 * @param value 要查找的值
	 * @return 下标 -1-没有找到
	 */
	public static List<Integer> searchList(int[] arr, int left, int right, int value) {
		// 注意这里的退出条件
		List<Integer> list = new ArrayList<>();
		if (left > right || value < arr[left] || value > arr[right]) {
			return list;
		}
		int mid = (left + right) / 2;
		int midVal = arr[mid];
		if (value > midVal) { // 向右递归
			return searchList(arr, mid + 1, right, value);
		} else if (value < midVal) { // 向左递归
			return searchList(arr, left, mid - 1, value);
		} else {
			list.add(mid);
			// 向左查找
			int temp = mid - 1;
			while (true) {
				if (temp < 0 || arr[temp] != value) {
					break;
				}
				list.add(temp);
				temp -= 1;
			}
			// 向右查找
			temp = mid + 1;
			while (true) {
				if (temp > right || arr[temp] != value) {
					break;
				}
				list.add(temp);
				temp += 1;
			}
			return list;
		}
	}

}
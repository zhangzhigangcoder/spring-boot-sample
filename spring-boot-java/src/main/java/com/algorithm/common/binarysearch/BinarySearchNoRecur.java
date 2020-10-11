package com.algorithm.common.binarysearch;

/**
 * 二分查找算法-非递归实现
 *
 */
public class BinarySearchNoRecur {

	public static void main(String[] args) {
		int[] arr = {1, 3, 8, 10, 11, 67, 100};
		int index = search(arr, 100);
		System.out.println("index=" + index);
		
		
	}
	
	/**
	 * 查找
	 * @param arr 待查询排序数组(此处为升序排列)
	 * @param target 要查找的数
	 * @return 要查找数的下标  若没有找到返回-1
	 */
	public static int search(int[] arr, int target) {
		int left = 0, right = arr.length - 1;
		while (left <= right) {
			int mid = (left + right) / 2;
			if (arr[mid] == target) {
				return mid;
			}
			if (target < arr[mid]) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return -1;
	}
}
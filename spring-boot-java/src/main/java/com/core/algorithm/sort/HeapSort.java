package com.core.algorithm.sort;

/**
 * 堆排序 用数组模拟顺序二叉树
 * /**
 * 时间复杂度为 O(nlog2_n) 
 * 
 * 80000个数据执行时间：16ms
 * 800000个数据执行时间：130ms
 * 8000000个数据执行时间：2100ms
 * 
 * @author zhangzhigang
 *
 */
public class HeapSort {
	
	public static void main(String[] args) {
//		int[] arr = { 3, 10, -1, 20, 2 };
		int[] arr = new int[8000000];
		for (int i = 0; i < 8000000; i++) {
			arr[i] = (int) (Math.random() * 8000_0000);
		}
		long start = System.currentTimeMillis();
		sort(arr);
		System.out.println(System.currentTimeMillis() - start);
//		System.out.println(Arrays.toString(arr));
	}
	
	public static void sort(int[] arr) {
		
		// 将整棵树调整为大顶堆树(从下往上遍历)
		for (int i = arr.length / 2 -1; i >= 0; i--) {
			adjustHeap(arr, i, arr.length);
		}
		
		// 第一个元素和最后一个元素交换 -> 重新调整为大顶堆树 -> 交换
		int temp = 0;
		for (int j = arr.length - 1; j > 0; j--) {
			temp = arr[j];
			arr[j] = arr[0];
			arr[0] = temp;
			adjustHeap(arr, 0, j);
		}
		
	}
	
	/**
	 * 将指定节点调整为大顶堆
	 * @param arr 待调整数组
	 * @param index 要调整的非叶子节点索引
	 * @param length
	 */
	private static void adjustHeap(int[] arr, int index, int length) {
		int temp = arr[index];
		for (int k = 2 * index + 1; k < length; k = 2 * k + 1) {
			if (k + 1 < length && arr[k] < arr[k+1]) {
				// 右子节点大于左子节点，将索引指向右子节点
				k++;
			}
			if (arr[k] > temp) {
				// 子节点中有大于父节点的，重置父节点
				arr[index] = arr[k];
				// 由于节点的调整，可能导致之前已经调整好的大顶堆发生变化，所以好继续遍历调整
				index = k;
			} else {
				// 如果当前节点已经是大顶堆，就不用向下继续遍历了，因为子树已经调整过了
				break;
			}
		}
		// 重置子节点值
		arr[index] = temp;
	}
}
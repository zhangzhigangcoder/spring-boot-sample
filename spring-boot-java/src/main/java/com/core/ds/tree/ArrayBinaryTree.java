package com.core.ds.tree;

/**
 * 顺序存储二叉树
 *
 */
public class ArrayBinaryTree {
	private int[] arr;
	
	public ArrayBinaryTree(int[] arr) {
		this.arr = arr;
	}
	
	/**
	 * 前序遍历
	 * 
	 */
	public void preOrder() {
		preOrder(0);
	}
	
	/**
	 * 前序遍历
	 * @param index 从第几个元素开始遍历， 从0开始
	 */
	public void preOrder(int index) {
		if (index < 0 || index > arr.length -1) {
			System.out.println("index无效");
			return;
		}
		if (null == arr || arr.length == 0) {
			System.out.println("数组为空，不能遍历");
			return;
		}
		// 输出当前元素                    
		System.out.print(arr[index] + " ");
		// 向左递归遍历
		if ((2 * index + 1) < arr.length) {
			preOrder(2 * index + 1);
		}
		
		// 向右递归遍历
		if ((2 * index + 2) < arr.length) {
			preOrder(2 * index + 2);
		}
	}
	
}
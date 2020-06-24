package com.core.data.struct.tree;

/**
 * 顺序存储二叉树测试
 *
 */
public class ArrayBinaryTreeTest {
	
	public static void main(String[] args) {
		int[] arr = {1, 2, 3, 4, 5, 6, 7};
		ArrayBinaryTree tree = new ArrayBinaryTree(arr);
		// 1 2 4 5 3 6 7 
		tree.preOrder();
	}
}
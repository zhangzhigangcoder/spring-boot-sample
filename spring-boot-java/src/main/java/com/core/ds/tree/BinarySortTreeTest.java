package com.core.ds.tree;

import com.core.ds.tree.BinarySortTree.Node;

/**
 * 二叉排序树测试
 *
 */
public class BinarySortTreeTest {
	public static void main(String[] args) {
		int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
		BinarySortTree binarySortTree = new BinarySortTree();
		for (int i = 0; i < arr.length; i++) {
			binarySortTree.add(new Node(arr[i]));
		}
		binarySortTree.infixOrder();
		
		binarySortTree.delNode(2);
		binarySortTree.delNode(5);
		binarySortTree.delNode(9);
		binarySortTree.delNode(12);
		binarySortTree.delNode(7);
		binarySortTree.delNode(3);
		binarySortTree.delNode(10);
		binarySortTree.delNode(1);
		
		System.out.println();
		binarySortTree.infixOrder();
	}
}
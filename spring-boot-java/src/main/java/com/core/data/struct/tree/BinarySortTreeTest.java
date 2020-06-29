package com.core.data.struct.tree;

import com.core.data.struct.tree.BinarySortTree.Node;

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
	}
}
package com.core.data.struct.tree.avl;

import com.core.data.struct.tree.avl.AVLTree.Node;

/**
 * 平衡二叉树测试
 *
 */
public class AVLTreeTest {
	public static void main(String[] args) {
//		int[] arr = {4, 3, 6, 5, 7, 8};
//		int[] arr = {10, 12, 8, 9, 7, 6};
		int[] arr = {10, 11, 7, 6, 8, 9};
		AVLTree tree = new AVLTree();
		for (int i = 0; i < arr.length; i++) {
			tree.add(new Node(arr[i]));
		}
		tree.infixOrder();
		
		System.out.println("处理前~~");
		System.out.println("树的高度=" + tree.getRoot().height());
		System.out.println("树的左子树高度=" + tree.getRoot().leftHeight());
		System.out.println("树的右子树高度=" + tree.getRoot().rightHeight());
		System.out.println("根节点=" + tree.getRoot());
	}
}
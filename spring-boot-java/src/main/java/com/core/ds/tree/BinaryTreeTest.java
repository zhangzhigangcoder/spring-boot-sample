package com.core.ds.tree;

import com.core.ds.tree.BinaryTree.Node;

/**
 * 二叉树测试
 *
 */
public class BinaryTreeTest {

	public static void main(String[] args) {
		BinaryTree tree = new BinaryTree();
		Node root = new Node(1, "宋江");
		Node n2 = new Node(2, "吴用");
		Node n3 = new Node(3, "卢俊义");
		Node n4 = new Node(4, "林冲");

		tree.setRoot(root);
		root.setLeft(n2);
		root.setRight(n3);
		n3.setRight(n4);

		System.out.println("前序遍历");
		tree.preOrder();
		
		tree.delete(2);
		System.out.println("前序遍历");
		tree.preOrder();
	}

}
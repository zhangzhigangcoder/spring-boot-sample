package com.core.data.struct.tree;

import com.core.data.struct.tree.ThreadedBinaryTree.Node;

/**
 * 线索化二叉树测试
 *
 */
public class ThreadedBinaryTreeTest {
	
	public static void main(String[] args) {
		ThreadedBinaryTree tree = new ThreadedBinaryTree();
		Node root = new Node(1, "n1");
		Node n3 = new Node(3, "n3");
		Node n6 = new Node(6, "n6");
		Node n8 = new Node(8, "n8");
		Node n10 = new Node(10, "n10");
		Node n14 = new Node(14, "n14");

		tree.setRoot(root);
		root.setLeft(n3);
		root.setRight(n6);
		n3.setLeft(n8);
		n3.setRight(n10);
		n6.setLeft(n14);
		tree.infixThreadedNodes(root);
		tree.infixOrder();
	}
}
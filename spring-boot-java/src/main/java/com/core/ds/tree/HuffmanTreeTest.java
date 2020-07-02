package com.core.ds.tree;

import com.core.ds.tree.HuffmanTree.Node;

/**
 * 赫夫曼树测试
 *
 */
public class HuffmanTreeTest {
	public static void main(String[] args) {
		int[] arr = {13, 7, 8, 3, 29, 6, 1};
		Node root = HuffmanTree.createHuffmanTree(arr);
		root.preOrder();
	}
}
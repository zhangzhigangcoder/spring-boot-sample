package com.core.data.struct.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 赫夫曼树
 *
 */
public class HuffmanTree {
	
	/**
	 * 创建霍夫曼树
	 * @param arr
	 * @return
	 */
	public static Node createHuffmanTree(int[] arr) {
		// 1. 将数组转化为节点集合
		List<Node> nodes = new ArrayList<HuffmanTree.Node>();
		for (int val : arr) {
			nodes.add(new Node(val));
		}
		
		// 2. 将集合中节点按照霍夫曼规则创建成霍夫曼树
		while (nodes.size() > 1) {
			// 2.1 从小到大排序
			Collections.sort(nodes);
			// 2.2 将最小两个节点作为子节点构成一颗新的二叉树，父节点权值为子节点权值之和
			Node left = nodes.get(0);
			Node right = nodes.get(1);
			Node parent = new Node(left.value + right.value);
			parent.left = left;
			parent.right = right;
			
			// 2.3 移除两个子节点，并将新的根节点加入集合中，最终集合中只会剩下一个根节点
			nodes.remove(left);
			nodes.remove(right);
			nodes.add(parent);
		}
		return nodes.get(0);
	}
	
	public static void preOrder(Node root) {
		if (null == root) {
			System.out.println("是空树，不能遍历");
			return;
		}
		root.preOrder();
	}

	static class Node implements Comparable<Node> {
		private int value; // 节点权值
		private Node left; // 左子节点
		private Node right; // 右子节点
		
		public Node(int value) {
			this.value = value;
		}
		
		public void preOrder() {
			System.out.println(this);
			if (null != this.left) {
				this.left.preOrder();
			}
			if (null != this.right) {
				this.right.preOrder();
			}
		}
		
		@Override
		public int compareTo(Node o) {
			return this.value - o.value;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Node [value=");
			builder.append(value);
			builder.append("]");
			return builder.toString();
		}
		
	}
}
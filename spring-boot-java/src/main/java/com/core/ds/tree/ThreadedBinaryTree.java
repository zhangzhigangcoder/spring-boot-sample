package com.core.ds.tree;

/**
 * 线索化二叉树
 *
 */
public class ThreadedBinaryTree {
	private Node root;
	
	// 指向前一个节点(其中一个子节点)
	private Node preNode;  

	public void setRoot(Node root) {
		this.root = root;
	}
	
	/**
	 * 中序线索化当前节点
	 * @param node
	 */
	public void infixThreadedNodes(Node node) {
		if (null == node) {
			return;
		}
		// 先线索化左子树
		infixThreadedNodes(node.getLeft());
		// 线索化当前节点
		//  前驱节点
		if (null == node.getLeft()) {
			node.setLeft(preNode);
			node.setLeftType(1);
		}
		// 后继节点
		// 此处很巧妙：当前节点的preNode是左子节点
		if (null != preNode && null == preNode.getRight()) {
			preNode.setRight(node);
			preNode.setRightType(1);
		}
		
		preNode = node;
		
		// 线索化右子树
		infixThreadedNodes(node.getRight());
	}
	
	/**
	 * 中序遍历
	 */
	public void infixOrder() {
		Node temp = root;
		while (null != temp) {
			// 找到第一个leftType=1的节点(8)
			while (0 == temp.getLeftType()) {
				temp = temp.getLeft();
			}
			// 输出当前节点
			System.out.println(temp);
			while (1 == temp.getRightType()) {
				temp = temp.getRight();
				System.out.println(temp);
			}
			temp = temp.getRight();
		}
	}

	/**
	 * 节点
	 */
	static class Node {
		private int no;
		private String name;
		private Node left;
		private Node right;
		// 0：指向左子树 1：指向前驱节点
		private int leftType;
		// 0：指向右子树 1：指向后继节点
		private int rightType;

		public Node(int no, String name) {
			this.no = no;
			this.name = name;
		}

		public Node getLeft() {
			return left;
		}

		public void setLeft(Node left) {
			this.left = left;
		}

		public Node getRight() {
			return right;
		}

		public void setRight(Node right) {
			this.right = right;
		}
		
		public int getLeftType() {
			return leftType;
		}

		public void setLeftType(int leftType) {
			this.leftType = leftType;
		}

		public int getRightType() {
			return rightType;
		}

		public void setRightType(int rightType) {
			this.rightType = rightType;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Node [no=");
			builder.append(no);
			builder.append(", name=");
			builder.append(name);
			builder.append("]");
			return builder.toString();
		}
	}
}
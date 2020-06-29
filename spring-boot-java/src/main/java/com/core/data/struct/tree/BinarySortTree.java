package com.core.data.struct.tree;

/**
 * 二叉排序树
 *
 */
public class BinarySortTree {
	private Node root;

	public void setRoot(Node root) {
		this.root = root;
	}

	/**
	 * 添加节点
	 * @param node
	 */
	public void add(Node node) {
		if (null == root) {
			root = node;
		} else {
			root.add(node);
		}
	}
	
	/**
	 * 中序遍历
	 */
	public void infixOrder() {
		if (null == this.root) {
			System.out.println("空树，不能遍历");
			return;
		}
		this.root.infixOrder();
	}

	/**
	 * 节点
	 */
	static class Node {
		private int value;
		private Node left;
		private Node right;

		public Node(int value) {
			this.value = value;
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
		
		/**
		 * 添加节点
		 * @param node
		 */
		public void add(Node node) {
			if (null == node) {
				return;
			}
			// 小于当前值，放在左边
			if (node.value < this.value) {
				if (null == this.left) {
					this.left = node;
				} else {
					// 递归左树
					this.left.add(node);
				}
			} else { // 大于等于当前值，放在右边
				if (this.right == null) {
					this.right = node;
				} else {
					// 递归右树
					this.right.add(node);
				}
			}
		}
		
		/**
		 * 中序遍历
		 */
		public void infixOrder() {
			if (null != this.left) {
				this.left.infixOrder();
			}
			System.out.println(this);
			if (null != this.right) {
				this.right.infixOrder();
			}
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
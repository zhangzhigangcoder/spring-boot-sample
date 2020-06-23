package com.core.data.struct.tree;

/**
 * 数组模拟栈操作
 *
 */
public class BinaryTree {
	private Node root;

	public BinaryTree() {
	}

	public void setRoot(Node root) {
		this.root = root;
	}
	
	/**
	 * 前序查找
	 * @param no
	 * @return
	 */
	public Node preOrderSearch(int no) {
		if (null != root) {
			return root.preOrderSearch(no);
		}
		return null;
	}
	
	/**
	 * 中序查找
	 * @param no
	 * @return
	 */
	public Node infixOrderSearch(int no) {
		if (null != root) {
			return root.infixOrderSearch(no);
		}
		return null;
	}
	
	/**
	 * 后序查找
	 * @param no
	 * @return
	 */
	public Node postOrderSearch(int no) {
		if (null != root) {
			return root.postOrderSearch(no);
		}
		return null;
	}

	/**
	 * 前序遍历
	 */
	public void preOrder() {
		if (null == this.root) {
			System.out.println("空树，不能遍历");
			return;
		}
		this.root.preOrder();
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
	 * 后序遍历
	 */
	public void postOrder() {
		if (null == this.root) {
			System.out.println("空树，不能遍历");
			return;
		}
		this.root.postOrder();
	}

	/**
	 * 节点
	 */
	static class Node {
		private int no;
		private String name;
		private Node left;
		private Node right;

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
		
		/**
		 * 前序查找
		 * @param no
		 * @return
		 */
		public Node preOrderSearch(int no) {
			// 当前节点
			if (this.no == no) {
				return this;
			}
			// 左遍历
			Node resNode = null;
			if (null != this.left) {
				resNode = this.left.preOrderSearch(no);
			}
			if (null != resNode) {
				return resNode;
			}
			// 右遍历
			if (null != this.right) {
				resNode = this.right.preOrderSearch(no);
			}
			return resNode;
		}
		
		/**
		 * 中序查找
		 * @param no
		 * @return
		 */
		public Node infixOrderSearch(int no) {
			// 左遍历
			Node resNode = null;
			if (null != this.left) {
				resNode = this.left.preOrderSearch(no);
			}
			if (null != resNode) {
				return resNode;
			}
			// 当前节点
			if (this.no == no) {
				return this;
			}
			// 右遍历
			if (null != this.right) {
				resNode = this.right.preOrderSearch(no);
			}
			return resNode;
		}
		
		/**
		 * 后序查找
		 * @param no
		 * @return
		 */
		public Node postOrderSearch(int no) {
			// 左遍历
			Node resNode = null;
			if (null != this.left) {
				resNode = this.left.preOrderSearch(no);
			}
			if (null != resNode) {
				return resNode;
			}
			// 右遍历
			if (null != this.right) {
				resNode = this.right.preOrderSearch(no);
			}
			if (null != resNode) {
				return resNode;
			}
			// 当前节点
			if (this.no == no) {
				return this;
			}
			return resNode;
		}

		/**
		 * 前序遍历
		 */
		public void preOrder() {
			System.out.println(this);
			if (null != this.left) {
				this.left.preOrder();
			}
			if (null != this.right) {
				this.right.preOrder();
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

		/**
		 * 后序遍历
		 */
		public void postOrder() {
			if (null != this.left) {
				this.left.postOrder();
			}
			if (null != this.right) {
				this.right.postOrder();
			}
			System.out.println(this);
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
		
		System.out.println("中序遍历");
		tree.infixOrder();
		
		System.out.println("后序遍历");
		tree.postOrder();
		
		System.out.println(tree.preOrderSearch(4));
	}

}
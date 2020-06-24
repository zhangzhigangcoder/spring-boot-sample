package com.core.data.struct.tree;

/**
 * 二叉树
 *
 */
public class BinaryTree {
	private Node root;

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
	 * 删除节点
	 * @param no
	 */
	public void delete(int no) {
		if (null == root) {
			return;
		}
		if (no == root.no) {
			root = null;
			return;
		}
		root.delete(no);
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
		 * 删除节点
		 * @param no
		 * @return
		 */
		public boolean delete(int no) {
			// 删除左子节点
			if (null != this.left && this.left.no == no) {
				this.left = null;
				return true;
			}
			
			// 删除左子节点
			if (null != this.right && this.right.no == no) {
				this.right = null;
				return true;
			}
			
			boolean delFlag = false;
			
			// 递归删除左子树
			if (null != this.left) {
				delFlag = this.left.delete(no);
			}
			
			// 递归删除右子树
			if (!delFlag && null != this.right) {
				delFlag = this.right.delete(no);
			}
			return delFlag;
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
}
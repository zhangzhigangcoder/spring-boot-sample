package com.ds.tree.avl;

/**
 * 二叉排序树
 *
 */
public class AVLTree {
	private Node root;

	public Node getRoot() {
		return root;
	}
	
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
	 * 查找要删除节点
	 * @param value
	 * @return
	 */
	public Node search(int value) {
		if (null == root) {
			return null;
		} else {
			return root.search(value);
		}
	}
	
	/**
	 * 查找要删除节点父节点
	 * @param value
	 * @return
	 */
	public Node searchParent(int value) {
		if (null == root) {
			return null;
		} else {
			return root.searchParent(value);
		}
	}
	
	/**
	 * 删除节点
	 * @param value
	 */
	public void delNode(int value) {
		if (null == root) {
			return;
		} else {
			// 查找要删除节点
			Node targetNode = search(value);
			if (null == targetNode) {
				return;
			}
			// 说明要删除的就是root节点
			if (null == root.left && null == root.right) {
				root = null;
				return;
			}
			Node parent = searchParent(value);
			// 1.1  删除叶子节点
			if (null == targetNode.left && null == targetNode.right) {
				if (null != parent.left && parent.left.value == value) {
					parent.left = null;
				} else if (null != parent.right && parent.right.value == value) {
					parent.right = null;
				}
			} 
			// 1.2  删除有两颗子树的节点
			else if (null != targetNode.left && null != targetNode.right) {
				targetNode.value = delRightTreeMin(targetNode.right);
			}
			// 1.3 删除只有一颗子树的节点
			else {
				// 1.3.1 要删除的节点有左子节点
				// parent为空时，直接删除
				if (null == parent) {
					root = null != targetNode.left ? targetNode.left : targetNode.right;
					return;
				}
				if (null != targetNode.left) {
					if (value == parent.left.value) {
						parent.left = targetNode.left;
					} else {
						parent.right = targetNode.left;
					}
				} 
				// 1.3.2 要删除的节点有右子节点
				else {
					if (value == parent.left.value) {
						parent.left = targetNode.right;
					} else {
						parent.right = targetNode.right;
					}
				}
			}
		}
	}
	
	/**
	 * 删除node为根节点的二叉排序树的最小节点，并返回最小值
	 * 
	 * @param node
	 * @return
	 */
	public int delRightTreeMin(Node node) {
		Node target = node;
		// 循环查找左子节点，找到最小值
		while (null != target.left) {
			target = target.left;
		}
		// 删除最小值节点
		delNode(target.value);
		return target.value;
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
		 * 返回当且节点左子树的高度
		 * @return
		 */
		public int leftHeight() {
			if (null == left) {
				return 0;
			}
			return left.height();
		}
		
		/**
		 * 返回右子树的高度
		 * @return
		 */
		public int rightHeight() {
			if (null == right) {
				return 0;
			}
			return right.height();
		}
		
		/**
		 * 返回以当前节点为根节点的树的高度
		 * @return
		 */
		public int height() {
			return Math.max(null == left ? 0 : left.height(), null == right ? 0 : right.height()) + 1;
		}
		
		/**
		 * 左旋转
		 */
		private void leftRotate() {
			// 创建新节点，等于当前节点的值
			Node newNode = new Node(this.value);
			// 把新节点的左子树设置为当前节点的左子树
			newNode.left = this.left;
			// 把新节点的右子树设置为当前节点的右子树的左子树
			newNode.right = this.right.left;
			// 把当前节点的值设置成右子节点的值
			this.value = this.right.value;
			// 把当前节点的右子树设置成右子树的右子树
			this.right = this.right.right;
			// 把当前节点的左子节点设置为新节点
			this.left = newNode;
		}
		
		/**
		 * 右旋转
		 */
		private void rightRotate() {
			// 创建新节点，等于当前节点的值
			Node newNode = new Node(this.value);
			// 把新节点的左子树设置为当前节点的左子树
			newNode.right = this.right;
			// 把新节点的右子树设置为当前节点的右子树的左子树
			newNode.left = this.left.right;
			// 把当前节点的值设置成右子节点的值
			this.value = this.left.value;
			// 把当前节点的右子树设置成右子树的右子树
			this.left = this.left.left;
			// 把当前节点的左子节点设置为新节点
			this.right = newNode;
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
			if (Math.abs(this.leftHeight() - this.rightHeight()) > 1) {
				if (this.rightHeight() - this.leftHeight() > 1) {
					// 判断子树是否需要旋转
					if (null != this.right && this.right.leftHeight() > this.left.rightHeight()) {
						this.right.rightRotate();
					}
					this.leftRotate(); // 左旋转
				} else {
					// 判断子树是否需要旋转
					if (null != this.left && this.left.rightHeight() > this.left.leftHeight()) {
						this.left.leftRotate();
					}
					this.rightRotate(); // 右旋转
				}
			}
		}
		
		/**
		 * 查找要删除的节点
		 * @param value
		 * @return
		 */
		public Node search(int value) {
			if (this.value == value) {
				return this;
			} else if (value < this.value) {
				if (null == this.left) {
					return null;
				}
				return this.left.search(value);
			} else {
				if (null == this.right) {
					return null;
				}
				return this.right.search(value);
			}
		}
		
		/**
		 * 查找要删除节点的父节点
		 * @param value
		 * @return
		 */
		public Node searchParent(int value) {
			if ((null != this.left && this.left.value == value) || (null != this.right && this.right.value == value)) {
				return this;
			} else {
				if (value < this.value && null != this.left) {
					return this.left.searchParent(value);
				} else if (value >= this.value && null != this.right) {
					return this.right.searchParent(value);
				}
			}
			return null;
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
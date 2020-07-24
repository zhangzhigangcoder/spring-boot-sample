package com.core.ds.tree;

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
			// 要删除的就是root节点
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
				// 将右子树中最小值节点值作为targetNode节点值，并删除右子树最小值节点
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
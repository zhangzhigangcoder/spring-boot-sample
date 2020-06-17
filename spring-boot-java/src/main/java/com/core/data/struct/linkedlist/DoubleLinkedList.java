package com.core.data.struct.linkedlist;

/**
 * 双向链表
 *
 */
public class DoubleLinkedList {

	// 初始化头节点
	private Node head = new Node(0);

	// 是否排序
	private boolean sorted;

	public DoubleLinkedList() {
		this(false);
	}

	public DoubleLinkedList(boolean sorted) {
		this.sorted = sorted;
	}

	/**
	 * 添加一个节点
	 * @param node
	 */
	public void add(Node node) {
		if (sorted) {
			addByOrder(node);
			return;
		}
		addToTail(node);
	}

	/**
	 * 添加到链表尾部
	 * @param node
	 */
	public void addToTail(Node node) {
		Node curNode = head;
		// 查找尾部节点
		while (null != curNode.next) {
			curNode = curNode.next;
		}
		// 添加到链表尾部
		curNode.next = node;
		node.prev = curNode;
	}

	/**
	 * 有序添加节点
	 * @param newNode
	 */
	private void addByOrder(Node newNode) {
		Node curNode = head.next;
		boolean isExist = false; // 节点是否已存在
		// 查找插入位置的后一个节点
		while (null != curNode) {
			if (curNode.no >= newNode.no) {
				if (curNode.no == newNode.no) {
					isExist = true;
				}
				break;
			}
			curNode = curNode.next;
		}
		if (isExist) {
			System.out.println("节点已存在: " + newNode);
			return;
		}
		if (null == curNode) { // 只有一个头节点
			head.next = newNode;
			newNode.prev = head;
			return;
		}
		// 添加到链表中
		curNode.prev.next = newNode;
		newNode.prev = curNode.prev;
		newNode.next = curNode;
		curNode.prev = newNode;
	}
	
	/**
	 * 根据no，修改节点
	 * @param newNode
	 */
	public void update(Node newNode) {
		if (null == head.next) {
			System.out.println("链表为空");
			return;
		}
		Node curNode = head.next; // 当前节点
		boolean isExist = false;
		while (true) {
			if (null == curNode) {
				break;
			}
			if (curNode.equals(newNode)) {
				isExist = true;
				break;
			}
			curNode = curNode.next;
		}
		if (!isExist) {
			System.out.println("节点不存在: " + newNode);
			return;
		}
		curNode.name = newNode.name;
	}

	/**
	 * 删除一个节点
	 * @param delNode
	 */
	public void delete(Node delNode) {
		if (null == head.next) {
			System.out.println("链表为空，无法删除");
			return;
	 	}
		Node curNode = head.next; // 当前节点
		boolean isExist = false;
		while (true) {
			if (null == curNode) {
				break;
			}
			// 找到待删除节点
			if (curNode.equals(delNode)) {
				isExist = true;
				break;
			}
			curNode = curNode.next;
		}
		if (!isExist) {
			System.out.println("节点不存在: " + delNode);
			return; 
		} 
		
		curNode.prev.next = curNode.next;
		// 有可能删除的是最后一个节点
		if (null != curNode.next) {
			curNode.next.prev = curNode.prev;
		}
	}
	
	/**
	 * 打印链表
	 */
	public void list() {
		if (null == head.next) {
			System.out.println("链表为空");
			return;
		}
		Node temp = head.next;
		while (null != temp) {
			System.out.println(temp);
			temp = temp.next;
		}
	}
	
	public Node getHead() {
		return this.head;
	}
	
	/**
	 *  Node节点
	 *
	 */
	private class Node {

		private int no;
		
		private String name;
		
		private Node prev;
		
		private Node next;
		
		public Node(int no) {
			this(no, null);
		}

		public Node(int no, String name) {
			this.no = no;
			this.name = name;
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			result = prime * result + no;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			if (no != other.no)
				return false;
			return true;
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

		private DoubleLinkedList getOuterType() {
			return DoubleLinkedList.this;
		}
	}
	
	public static void main(String[] args) {
		Node node1 = new DoubleLinkedList().new Node(1, "宋江");
		Node node2 = new DoubleLinkedList().new Node(2, "卢俊义");
		Node node3 = new DoubleLinkedList().new Node(3, "吴用");
		Node node9 = new DoubleLinkedList().new Node(9, "林冲");
		DoubleLinkedList doubleLinkedList = new DoubleLinkedList(true);
		doubleLinkedList.add(node9);
		doubleLinkedList.add(node1);
		doubleLinkedList.add(node2);
		doubleLinkedList.add(node3);
		doubleLinkedList.list();
		
		// 更新
//		node2 = new DoubleLinkedList().new Node(2,"zhangsan");
//		doubleLinkedList.update(node2);
//		doubleLinkedList.list();
		
		// 删除
//		doubleLinkedList.delete(node3);
//		doubleLinkedList.list();
	}

}



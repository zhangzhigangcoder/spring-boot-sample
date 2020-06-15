package com.core.ds.linkedlist;

import java.util.Stack;

/**
 * 单向链表
 *
 */
public class SingleLinkedList {

	// 初始化头节点
	private Node head = new Node(0);

	// 是否排序
	private boolean sorted;

	public SingleLinkedList() {
		this(false);
	}

	public SingleLinkedList(boolean sorted) {
		this.sorted = sorted;
	}

	/**
	 * 添加节点
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
	 * 添加节点到链尾
	 * @param node
	 */
	private void addToTail(Node node) {
		Node curNode = head;
		// 查找最后一个节点
		while (null != curNode.next) {
			curNode = curNode.next;
		}
		// 添加到链表尾部
		curNode.next = node;
	}

	/**
	 * 有序添加节点
	 * @param node
	 */
	private void addByOrder(Node node) {
		Node curNode = head;
		boolean isExist = false; // 节点是否已存在
		// 查找插入位置的前一个节点
		while (true) {
			if (null == curNode.next) { // 只有一个头节点
				break;
			}
			if (curNode.next.compareTo(node) >= 0) {
				if (curNode.next.compareTo(node) == 0) {
					isExist = true;
				}
				break;
			}
			curNode = curNode.next;
		}
		if (isExist) {
			System.out.println("节点已存在: " + node);
			return;
		}
		// 添加到链表中
		node.next = curNode.next;
		curNode.next = node;
	}
	
	/**
	 * 修改节点
	 * @param newNode
	 */
	public void update(Node newNode) {
		if (null == head.next) {
			System.out.println("链表为空");
			return;
		}
		Node curNode = head.next;
		boolean isExist = false;
		while (true) {
			if (null == curNode) {
				break;
			}
			if (curNode.compareTo(newNode) == 0) {
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
	 * 删除节点
	 * @param delNode
	 */
	public void delete(Node delNode) {
		if (null == head.next) {
			System.out.println("链表为空，无法删除");
			return;
		}
		Node preNode = head; // 前一个节点
		boolean isExist = false;
		while (true) {
			if (null == preNode.next) {
				break;
			}
			// 找到待删除节点
			if (preNode.next.equals(delNode)) {
				isExist = true;
				break;
			}
			preNode = preNode.next;
		}
		
		if (!isExist) {
			System.out.println("节点不存在: " + delNode);
			return;
		} 
		preNode.next = preNode.next.next;
	}

	/**
	 * 打印链表
	 */
	public void list() {
		if (null == head.next) {
			System.out.println("链表为空");
			return;
		}
		Node curNode = head.next;
		while (null != curNode) {
			System.out.println(curNode);
			curNode = curNode.next;
		}
	}
	
	/**
	 * 返回头节点
	 * @return
	 */
	public Node getHead() {
		return this.head;
	}
	
	/**
	 * Node节点
	 */
	private class Node implements Comparable<Node>{
		private int no;
		private String name;
		private Node next;
		
		public Node(int no) {
			this(no, null);
		}

		public Node(int no, String name) {
			this.no = no;
			this.name = name;
		}

		@Override
		public int compareTo(Node other) {
			return this.no - other.no;
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
			builder.append("HeroNode [no=");
			builder.append(no);
			builder.append(", name=");
			builder.append(name);
			builder.append("]");
			return builder.toString();
		}

		private SingleLinkedList getOuterType() {
			return SingleLinkedList.this;
		}

	}
	
	/**
	 *   面试题1：
	 *   获取单向链表有效节点个数
	 * @param head
	 * @return
	 */
	public static int getLength(Node head) {
		if (null == head.next) {
			return 0;
		}
		int length = 0;
		Node curNode = head.next;
		while (null != curNode) {
			length ++;
			curNode = curNode.next;
		}
		return length;
	}
	
	/**
	 *  面试题2：
	 *  查找单向链表中的倒数第k个节点
	 * @param head
	 * @param index
	 * @return
	 */
	public static SingleLinkedList.Node findLastIndexNode(Node head, int index) {
		// 判断是否为空
		if (null == head.next) {
			return null;
		}
		// 1. 查询链表有效长度
		int length = getLength(head);
		if (index <= 0 || index > length) {
			return null;
		}
		Node curNode = head.next;
		// 注意这里的循环次数
		for (int i = 1; i <= length - index; i++) {
			curNode = curNode.next;
		}
		return curNode;
	}
	
	/**
	 * 面试题3：
	 * 单链表逆序
	 * @param head
	 */
	public static void reverseLinkedList(Node head) {
		// 链表只有一个头节点或只有一个数据节点时，直接返回
		if (null == head.next || null == head.next.next) {
			return;
		}
		// 创建一个新链表头节点
		Node newHead = new SingleLinkedList().new Node(0, null);
		Node curNode = head.next;
		Node nextNode = null;
		while (null != curNode) {
			// 保存下一个节点引用
			nextNode = curNode.next; 
			// 把当前节点插入到新链表第一个数据节点位置
			curNode.next = newHead.next;
			newHead.next = curNode;
			// 当前节点向后移动一位
			curNode = nextNode;
		}
		// 将原链表第一个数据节点指向新链表第一个数据节点
		head.next = newHead.next;
	}
	
	/**
	 * 面试题4：
	 * 逆序打印单链表
	 * 分析：
	 * 利用栈先进后出特点
	 * @param head
	 */
	public static void reversePrint(Node head) {
		Stack<Node> stack = new Stack<>();
		Node currNode = head.next;
		while (null != currNode) {
			stack.push(currNode);
			currNode = currNode.next;
		}
		while (stack.size() > 0) {
			System.out.println(stack.pop());
		}
	}
	
	/**
	 * 面试题5：
	 * 合并两个有序单链表
	 * @param head1
	 * @param head2
	 */
	public static SingleLinkedList mergeSingleLinkedList(Node head1, Node head2) {
		SingleLinkedList linkedList = new SingleLinkedList(true);
		Node curNode1 = head1.next;
		Node nextNode1 = head1.next;
		Node curNode2 = head2.next;
		Node nextNode2 = head2.next;
		while (null != curNode1 || null != curNode2) {
			
			if (null != curNode1) {
				nextNode1 = curNode1.next;
				linkedList.add(curNode1);
				curNode1 = nextNode1;
			}
			if (null != curNode2) {
				nextNode2 = curNode2.next;
				linkedList.add(curNode2);
				curNode2 = nextNode2;
			}
		}
		return linkedList;
	}
	
	public static void main(String[] args) {
		Node node1 = new SingleLinkedList().new Node(1, "宋江");
		Node node2 = new SingleLinkedList().new Node(2, "卢俊义");
		Node node3 = new SingleLinkedList().new Node(3, "吴用");
		Node node9 = new SingleLinkedList().new Node(9, "林冲");
		SingleLinkedList singleLinkedList = new SingleLinkedList();
		singleLinkedList.add(node9);
		singleLinkedList.add(node1);
		singleLinkedList.add(node2);
		singleLinkedList.add(node3);
		singleLinkedList.list();
		singleLinkedList.delete(node3);
		singleLinkedList.list();
		
//		Node node5 = new SingleLinkedList().new Node(5, "宋江");
//		Node node6 = new SingleLinkedList().new Node(6, "卢俊义");
//		Node node7 = new SingleLinkedList().new Node(7, "吴用");
//		Node node8 = new SingleLinkedList().new Node(8, "林冲");
//		SingleLinkedList singleLinkedList2 = new SingleLinkedList(true);
//		singleLinkedList2.add(node5);
//		singleLinkedList2.add(node7);
//		singleLinkedList2.add(node8);
//		singleLinkedList2.add(node6);
//		singleLinkedList2.list();
//		node2 = new SingleLinkedList().new Node(2, "时迁");
//		singleLinkedList.update(node2);
//		reversePrint(singleLinkedList.getHead());
//		SingleLinkedList mergeSingleLinkedList = mergeSingleLinkedList(singleLinkedList.getHead(),singleLinkedList2.getHead());
//		mergeSingleLinkedList.list();
	}

}



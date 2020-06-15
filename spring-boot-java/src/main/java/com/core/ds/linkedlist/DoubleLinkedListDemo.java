package com.core.ds.linkedlist;

/**
 * 双向链表
 *
 */
public class DoubleLinkedListDemo {

	public static void main(String[] args) {
		DoubleNode hero1 = new DoubleNode(1, "宋江");
		DoubleNode hero2 = new DoubleNode(2, "卢俊义");
		DoubleNode hero3 = new DoubleNode(3, "吴用");
		DoubleNode hero9 = new DoubleNode(9, "林冲");
		DoubleLinkedList doubleLinkedList = new DoubleLinkedList(true);
		doubleLinkedList.add(hero9);
		doubleLinkedList.add(hero1);
		doubleLinkedList.add(hero2);
		doubleLinkedList.add(hero3);
		doubleLinkedList.list();
		
		// 更新
//		hero2 = new DoubleNode(2,"zhangsan");
//		doubleLinkedList.update(hero2);
//		doubleLinkedList.list();
		
		// 删除
//		doubleLinkedList.delete(hero3);
//		doubleLinkedList.list();
	}
}

class DoubleLinkedList {
	
	// 初始化头节点
	private DoubleNode head = new DoubleNode(0);

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
	public void add(DoubleNode node) {
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
	public void addToTail(DoubleNode node) {
		DoubleNode curNode = head;
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
	private void addByOrder(DoubleNode newNode) {
		DoubleNode curNode = head.next;
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
	public void update(DoubleNode newNode) {
		if (null == head.next) {
			System.out.println("链表为空");
			return;
		}
		DoubleNode curNode = head.next; // 当前节点
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
		}
		curNode.name = newNode.name;
	}

	/**
	 * 删除一个节点
	 * @param delNode
	 */
	public void delete(DoubleNode delNode) {
		if (null == head.next) {
			System.out.println("链表为空，无法删除");
			return;
	 	}
		DoubleNode curNode = head.next; // 当前节点
		boolean isExist = false;
		while (true) {
			if (null == curNode) {
				break;
			}
			if (curNode.equals(delNode)) {
				isExist = true;
				break;
			}
			curNode = curNode.next;
		}
		if (!isExist) {
			System.out.println("节点不存在: " + delNode);
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
		DoubleNode temp = head.next;
		while (null != temp) {
			System.out.println(temp);
			temp = temp.next;
		}
	}
	
	public DoubleNode getHead() {
		return this.head;
	}

}

// Node节点
class DoubleNode {

	public int no;
	
	public String name;
	
	public DoubleNode prev;
	
	public DoubleNode next;
	
	public DoubleNode(int no) {
		this(no, null);
	}

	public DoubleNode(int no, String name) {
		this.no = no;
		this.name = name;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		DoubleNode other = (DoubleNode) obj;
		if (no != other.no)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DoubleNode [no=");
		builder.append(no);
		builder.append(", name=");
		builder.append(name);
		builder.append("]");
		return builder.toString();
	}

}

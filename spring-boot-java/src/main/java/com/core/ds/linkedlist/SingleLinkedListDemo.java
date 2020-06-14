package com.core.ds.linkedlist;

import java.util.Stack;

/**
 * 单向链表
 *
 */
public class SingleLinkedListDemo {

	public static void main(String[] args) {
		HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
		HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
		HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
		HeroNode hero9 = new HeroNode(9, "林冲", "豹子头");
		SingleLinkedList singleLinkedList = new SingleLinkedList(true);
		singleLinkedList.add(hero9);
		singleLinkedList.add(hero1);
		singleLinkedList.add(hero2);
		singleLinkedList.add(hero3);
//		singleLinkedList.list();
		
		HeroNode hero5 = new HeroNode(5, "宋江", "及时雨");
		HeroNode hero6 = new HeroNode(6, "卢俊义", "玉麒麟");
		HeroNode hero7 = new HeroNode(7, "吴用", "智多星");
		HeroNode hero8 = new HeroNode(8, "林冲", "豹子头");
		SingleLinkedList singleLinkedList2 = new SingleLinkedList(true);
		singleLinkedList2.add(hero5);
		singleLinkedList2.add(hero7);
		singleLinkedList2.add(hero8);
		singleLinkedList2.add(hero6);
//		singleLinkedList2.list();
//		hero2 = new HeroNode(2, "时迁", "跳上蚤");
//		singleLinkedList.update(hero2);
//		reversePrint(singleLinkedList.getHead());
		SingleLinkedList mergeSingleLinkedList = mergeSingleLinkedList(singleLinkedList.getHead(),singleLinkedList2.getHead());
		mergeSingleLinkedList.list();
				
		
	}
	
	/**面试题：逆序打印单链表
	 * 利用栈先进后出特点
	 * @param head
	 */
	public static void reversePrint(HeroNode head) {
		Stack<HeroNode> stack = new Stack<>();
		HeroNode currNode = head.next;
		while (null != currNode) {
			stack.push(currNode);
			currNode = currNode.next;
		}
		while (stack.size() > 0) {
			System.out.println(stack.pop());
		}
	}
	
	/**
	 * 合并两个有序单链表
	 * @param head1
	 * @param head2
	 */
	public static SingleLinkedList mergeSingleLinkedList(HeroNode head1, HeroNode head2) {
		SingleLinkedList linkedList = new SingleLinkedList(true);
		HeroNode curNode1 = head1.next;
		HeroNode nextNode1 = head1.next;
		HeroNode curNode2 = head2.next;
		HeroNode nextNode2 = head2.next;
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
}

class SingleLinkedList {
	// 初始化头节点
	private HeroNode head = new HeroNode(0, null, null);

	// 是否排序
	private boolean sorted;

	public SingleLinkedList() {
		this(false);
	}

	public SingleLinkedList(boolean sorted) {
		this.sorted = sorted;
	}

	// 添加一个节点
	public void add(HeroNode node) {
		if (sorted) {
			addByOrder(node);
			return;
		}
		addWithoutOrder(node);
	}

	// 无序添加节点
	public void addWithoutOrder(HeroNode node) {
		HeroNode temp = head;
		// 查找最后一个节点
		while (null != temp.next) {
			temp = temp.next;
		}
		// 添加到链表尾部
		temp.next = node;
	}

	// 有序添加节点
	private void addByOrder(HeroNode node) {
		HeroNode temp = head;
		boolean isExist = false; // 节点是否已存在
		// 查找插入位置的前一个节点
		while (true) {
			if (null == temp.next) { // 只有一个头节点
				break;
			}
			if (temp.next.no >= node.no) {
				if (temp.next.no == node.no) {
					isExist = true;
				}
				break;
			}
			temp = temp.next;
		}
		if (isExist) {
			System.out.println("节点已存在: " + node);
			return;
		}
		// 添加到链表中
		node.next = temp.next;
		temp.next = node;
	}
	
	// 根据no，修改节点
	public void update(HeroNode newNode) {
		if (null == head.next) {
			System.out.println("链表为空");
			return;
		}
		HeroNode temp = head.next;
		boolean isExist = false;
		while (true) {
			if (null == temp) {
				break;
			}
			if (temp.no == newNode.no) {
				isExist = true;
				break;
			}
			temp = temp.next;
		}
		if (!isExist) {
			System.out.println("节点不存在: " + newNode);
		}
		temp.name = newNode.name;
		temp.nikename = newNode.nikename;
		
	}

	// 显示链表
	public void list() {
		if (null == head.next) {
			System.out.println("链表为空");
			return;
		}
		HeroNode temp = head.next;
		while (null != temp) {
			System.out.println(temp);
			temp = temp.next;
		}
	}
	
	public HeroNode getHead() {
		return this.head;
	}

}

// Node节点
class HeroNode {

	public int no;
	public String name;
	public String nikename;
	public HeroNode next;
	
	public HeroNode(int no) {
		this(no, null, null);
	}

	public HeroNode(int no, String name, String nikename) {
		this.no = no;
		this.name = name;
		this.nikename = nikename;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("HeroNode [no=");
		builder.append(no);
		builder.append(", name=");
		builder.append(name);
		builder.append(", nikename=");
		builder.append(nikename);
		builder.append("]");
		return builder.toString();
	}
}

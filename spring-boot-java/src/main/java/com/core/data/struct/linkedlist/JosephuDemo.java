package com.core.data.struct.linkedlist;

/**
 * 单向环形链表模拟约瑟夫问题
 *
 */
public class JosephuDemo {

	public static void main(String[] args) {
		SingleCircleLinkedList singleCircleLinkedList = new SingleCircleLinkedList();
		singleCircleLinkedList.addNode(20);
		singleCircleLinkedList.list();
		singleCircleLinkedList.count(2, 5, 20);
	}
	
}

class SingleCircleLinkedList {
	// 初始化first节点
	private Node first = new Node(-1);

	/**
	 * 初始化链表节点
	 * @param num 增加的节点数
	 */
	public void addNode(int num) {
		if (num < 1) {
			System.out.println("num要大于1");
			return;
		}
		Node curNode = null;
		for (int i = 1; i <= num; i++) {
			Node node = new Node(i);
			if (i == 1) {
				first = node;
				first.next = first;
			} else {
				curNode.next = node;
				node.next = first;
			}
			curNode = node;
		}
	}

	/**
	 * 打印链表
	 */
	public void list() {
		if (null == first) {
			System.out.println("链表为空");
			return;
		}
		Node curNode = first;
		while (true) {
			System.out.println(curNode);
			curNode = curNode.next;
			if (curNode == first) {
				break;
			}
		}
	}

	/**
	 * 
	 * @param startNstartNoum 从第几个开始数
	 * @param countNum 数几次
	 * @param nums 最初圈中有几个
	 */
	public void count(int startNo, int countNum, int num) {
		if (null == first || startNo < 1 || startNo > num) {
			System.out.println("输入参数有误，请重新输入");
			return;
		}
		// 定义辅助节点，指向链表最后一个节点，帮助节点出圈后再次形成闭环
		Node helper = first;
		while (true) {
			if (helper.next == first) {
				break;
			}
			helper = helper.next;
		}
		// 移动到指定开始位置
		for (int j = 0; j < startNo - 1; j++) {
			first = first.next;
			helper = helper.next;
		}
		while (true) {
			if (helper == first) {
				break;
			}
			// 开始报数
			for (int j = 0; j < countNum - 1; j++) {
				first = first.next;
				helper = helper.next;
			}
			System.out.printf("节点%d出圈\n", first.no);
			first = first.next;
			helper.next = first;
		}
		System.out.println("最后一个节点是：" + first.no);
	}
	
	/**
	 * Node节点
	 */
	private class Node {

		private int no;
		private Node next;
		
		public Node(int no) {
			this.no = no;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Node [no=");
			builder.append(no);
			builder.append("]");
			return builder.toString();
		}
	}
}


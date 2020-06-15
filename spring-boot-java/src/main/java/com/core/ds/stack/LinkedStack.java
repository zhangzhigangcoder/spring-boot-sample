package com.core.ds.stack;

/**
 * 链表模拟栈操作
 *
 */
public class LinkedStack {

	private Node top;
	
	public boolean isEmpty() {
		return top.val == -1;
	}
	
	public LinkedStack() {
		top = new Node(-1);
	}
	
	/**
	 * 入栈
	 * @param value
	 */
	public void push(int value) {
		Node node = new Node(value);
		if (-1 == top.val) {
			top = node;
			return;
		} 
		node.next = top;
		top = node;
	}
	
	/**
	 * 出栈
	 * @return
	 */
	public Node pop() {
		if (-1 == top.val) {
			throw new RuntimeException("栈空");
		}
		Node result = top;
		if (null != top.next) {
			top = top.next;
		}
		return result;
	}
	

	/**
	 * 打印栈
	 */
	public void list() {
		if (isEmpty()) {
			System.out.println("栈空");
			return;
		}
		Node curNode = top;
		while (null != curNode) {
			System.out.printf("stack=%d\n", curNode.val);
			curNode = curNode.next;
		}
	}
	
	private class Node {
		private int val;
		private Node next;
		
		public Node(int val) {
			this.val = val;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Node [val=");
			builder.append(val);
			builder.append("]");
			return builder.toString();
		}
	}
	
	public static void main(String[] args) {
		LinkedStack stack = new LinkedStack();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.push(5);
		stack.list();
		System.out.println(stack.pop());
		System.out.println("-----------------");
		stack.list();
	}
}
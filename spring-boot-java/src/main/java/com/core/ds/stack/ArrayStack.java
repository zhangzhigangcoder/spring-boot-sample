package com.core.ds.stack;

import com.core.ds.tree.BinaryTree;

/**
 * 数组模拟栈操作
 *
 */
public class ArrayStack {
	private int maxSize; // 栈的大小
	private int[] arr; // 数组
	private int top = -1; // 栈顶
	
	public ArrayStack(int maxSize) {
		this.maxSize = maxSize;
		arr = new int[maxSize];
	}
	
	/**
	 * 栈满
	 * @return
	 */
	public boolean isFull() {
		return top == maxSize - 1;
	}
	
	/**
	 * 栈空
	 * @return
	 */
	public boolean isEmpty() {
		return top == -1;
	}
	
	/**
	 * 入栈
	 * @param value
	 */
	public void push(int value) {
		if (isFull()) {
			System.out.println("栈满");
			return;
		}
		top++;
		arr[top] = value;
	}
	
	/**
	 * 出栈
	 * @return
	 */
	public int pop() {
		if (isEmpty()) {
			throw new RuntimeException("栈空");
		}
		int result = arr[top];
		top--;
		return result;
	}
	
	/**
	 * 查看栈顶元素值
	 * @return
	 */
	public int peek() {
		return arr[top];
	}
	
	/**
	 * 打印栈
	 */
	public void list() {
		if (isEmpty()) {
			System.out.println("栈空");
			return;
		}
		// 需要从栈顶开始查找
		for (int i = top; i >= 0; i--) {
			System.out.printf("stack[%d]=%d\n", i, arr[i]);
		}
	}

	public static void main(String[] args) {
		ArrayStack stack = new ArrayStack(10);
		stack.push(1);
		stack.push(2);
		stack.push(5);
		stack.push(4);
		stack.push(3);
		stack.list();
		System.out.println(stack.pop());
		System.out.println("-----------------");
		stack.list();
	}
}
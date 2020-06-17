package com.core.data.struct.queue;

import java.util.Scanner;

/**
 * 数组实现队列
 *
 */
public class ArrayQueueDemo {

	public static void main(String[] args) {
		ArrayQueue queue = new ArrayQueue(3);
		char key = ' ';
		Scanner scanner = new Scanner(System.in);
		boolean loop = true;
		while (loop) {
			System.out.println("请输入操作：s-show,e-exit,a-add,g-get,h-head");
			key = scanner.next().charAt(0);
			switch (key) {
			case 's':
				queue.showQueue();
				break;
			case 'e':
				scanner.close();
				loop = false;
				System.out.println("程序退出");
				break;
			case 'a':
				System.out.println("请输入一个数据:");
				int value = scanner.nextInt();
				queue.addQueue(value);
				break;
			case 'g':
				try {
					int res = queue.getQueue();
					System.out.println("取出的数据为: " + res);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 'h':
				try {
					int res = queue.headQueue();
					System.out.println("对头为: " + res);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;

			}
		}
	}
}

class ArrayQueue {
	// 队列最大容量
	private int maxSize;
	// 队头，指向第一个元素的前一个位置
	private int front;
	// 队尾，指向最后一个元素位置
	private int rear;
	// 队列数组
	private int[] arr;

	public ArrayQueue(int arrMaxSize) {
		this.maxSize = arrMaxSize;
		arr = new int[this.maxSize];
		this.front = -1;
		this.rear = -1;
	}

	// 判断队列是否满(当指向最后一个位置时，前面的元素有可能都被取出了)
	private boolean isFull() {
		return this.rear == this.maxSize - 1;
	}

	// 判断队列是否为空(有可能都指向最后一个元素位置)
	private boolean isEmpty() {
		return this.rear == this.front;
	}

	// 添加数据
	public void addQueue(int ele) {
		if (isFull()) {
			System.out.println("队列已满，不能添加");
			return;
		}
		rear++; // 这里体现了rear是指向最后一个元素位置的
		arr[rear] = ele;
	}

	// 获取数据
	public int getQueue() {
		if (isEmpty()) {
			throw new RuntimeException("队列为空");
		}
		front++; // 这里体现了front是指向第一个元素前一个位置的
		return arr[front];
	}

	// 显示队列所有数据
	public void showQueue() {
		if (isEmpty()) {
			System.out.println("队列为空");
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.printf("arr[%d]=%d\n", i, arr[i]);
		}
	}

	// 显示对头数据
	public int headQueue() {
		if (isEmpty()) {
			throw new RuntimeException("队列为空");
		}
		return arr[front + 1];
	}
}

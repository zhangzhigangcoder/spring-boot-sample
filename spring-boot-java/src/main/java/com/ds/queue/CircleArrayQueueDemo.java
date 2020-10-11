package com.ds.queue;

import java.util.Scanner;

/**
 * 循环数组实现队列
 *
 */
public class CircleArrayQueueDemo {

	public static void main(String[] args) {
		CircleArrayQueue queue = new CircleArrayQueue(4);
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

class CircleArrayQueue {
	// 队列最大容量
	private int maxSize;
	// 调整1
	// 对头，指向第一个元素位置
	// 初始化为0
	private int front;
	// 调整2
	// 队尾， 指向最后一个元素后面一个位置，希望后面空出一个位置
	// 初始化为0
	private int rear;
	// 队列数组
	private int[] arr;

	public CircleArrayQueue(int arrMaxSize) {
		this.maxSize = arrMaxSize;
		arr = new int[this.maxSize];
	}

	// 判断队列是否满
	private boolean isFull() {
		// 调整3
		// 因为可能会出现 rear < front 情况
		// 这里体现了rear是指向最后一个元素的后一个位置
		return (this.rear + 1) % this.maxSize == this.front;
	}

	// 判断队列是否为空
	private boolean isEmpty() {
		// 这里体现了rear是指向最后一个元素的后一个位置
		return this.rear == this.front;
	}

	// 添加数据
	public void addQueue(int ele) {
		if (isFull()) {
			System.out.println("队列已满，不能添加");
			return;
		}
		// 调整4 先赋值，后环形向后移动
		arr[rear] = ele;
		rear = (rear + 1) % maxSize;
	}

	// 获取数据
	public int getQueue() {
		if (isEmpty()) {
			throw new RuntimeException("队列为空");
		}
		// 调整5 先取值，后环形向后移动
		int value = arr[front];
		front = (front + 1) % maxSize;
		return value;
	}

	// 显示队列所有数据
	public void showQueue() {
		if (isEmpty()) {
			System.out.println("队列为空");
			return;
		}
		// 调整6
		for (int i = front; i < front + size(); i++) {
			System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
		}
	}
	
	// 求出当前队列有效数据个数
	private int size() {
		return (rear + maxSize - front) % maxSize;
	}

	// 显示对头数据
	public int headQueue() {
		if (isEmpty()) {
			throw new RuntimeException("队列为空");
		}
		return arr[front];
	}
}

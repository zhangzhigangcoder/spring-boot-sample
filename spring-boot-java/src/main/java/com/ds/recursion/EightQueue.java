package com.ds.recursion;

import java.util.Arrays;

/**
 * 递归解决八皇后问题
 *
 */
public class EightQueue {

	// 皇后个数
	private int max;
	
	// 皇后位置 arr[i] = val, val表示第i+1个皇后，放在第(i,val)位置
	private int[] arr;
	private static int count;
	public EightQueue(int max) {
		this.max = max;
		arr = new int[this.max];
	}
	
	public static void main(String[] args) {
		EightQueue eightQueue = new EightQueue(8);
		eightQueue.build(0);
		System.out.println("一共有" + count + "解法");
	}
	
	/**
	 * 放置第n个皇后，注意这里面的回溯思想
	 * @param n 第n个皇后 从0开始
	 */
	public void build(int n) {
		if (n == max) { // 行数 =  max 已经完成了
			print();
			return;
		}
		// 依次放入皇后，并判断是否冲突
		for (int i = 0; i < max; i++) { 
			// 默认把当前皇后n从每一行的从左到右放置
			arr[n] = i;
			// 判断当前放置第n个皇后到i列时，和之前的是否冲突
			if (valid(n)) { 
				// 不冲突，接着放置下一个皇后
				build(n + 1);
			}
		}
	}
	
	/**
	 * 判断当前皇后位置和前面皇后位置是否冲突
	 * @param n 第n个皇后
	 * @return
	 */
	private boolean valid(int n) {
		for (int i = 0; i < n; i++) { // 本身就代表了不同行
			// 同一列 || 斜线(行差==列差)
			if (arr[i] == arr[n] || n - i == Math.abs(arr[n] - arr[i])) {
				return false;
			}
		}
		return true;
	}
	
	private void print() {
		System.out.println(Arrays.toString(arr));
		count++;
	}
}
package com.core.algorithm.common.dac;

/**
 * 分治算法-汉诺塔
 *
 */
public class Hanoitower {

	public static void main(String[] args) {
		hanoiTower(2, 'A', 'B', 'C');
	}
	
	public static void hanoiTower(int num, char a, char b, char c) {
		// 如果只有一个盘
		if (1 == num) {
			System.out.println("第1个盘从 " + a + "->" + c);
			return;
		}
		// 如果超过1个盘，将盘拆分成两个盘：最下面为一个盘，其余的为一个盘
		// 1.  将A塔上面所有盘移动到B塔  A->B
		hanoiTower(num - 1, a, c, b);
		// 2. 将A塔最下面的盘移动到C塔  A->C
		System.out.println("第" + num + "个盘从 " + a + "->" + c);
		// 3. 将B塔所有的盘移动到C盘 B->C
		hanoiTower(num - 1, b, a, c);
	}
}
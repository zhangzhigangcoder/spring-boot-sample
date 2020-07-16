package com.core.algorithm.common.dynamic;

/**
 * 动态规划算法-背包问题
 *
 */
public class Knapsack {
	
	public static void main(String[] args) {
		int[] w = {1, 4, 3}; // 物品重量
		int[] val = {1500, 3000, 2000}; // 物品价值
		int m = 4; // 背包容量
		knapsack(w, val, m);
	}

	/**
	 * 
	 * @param w 物品重量数组
	 * @param val 物品价值数组，数量和w要保持一致
	 * @param m 背包最大容量
	 */
	public static void knapsack(int[] w, int[] val, int m) {
		int n = val.length; // 物品个数
		// v[i][j] 表示在前i个物品中能够装入容量为j的背包中的最大价值
		int[][] v = new int[n+1][m+1];
		
		// v[i][j] 表示在前i个物品中能够装入容量为j的背包中的最大价值
		int[][] path = new int[n+1][m+1];
		
		// 行和列都从1开始，默认值为0
		for (int i = 1; i < v.length; i++) { // 第i个物品
			for (int j = 1; j < v[0].length; j++) { // 容量为j的背包
				if (w[i-1] > j) { // 第i-1个物品容量小于当前背包容量j
					v[i][j] = v[i-1][j];
					continue;
				} 
//				v[i][j] = Math.max(v[i-1][j], val[i-1] + v[i-1][j-w[i-1]]);
				if (v[i-1][j] < val[i-1] + v[i-1][j-w[i-1]]) {
					v[i][j] = val[i-1] + v[i-1][j-w[i-1]];
					path[i][j] = 1;
				} else {
					v[i][j] = v[i-1][j];
				}
			}
		}
		
		int i = path.length - 1; // 行的最大下标
		int j = path[0].length - 1; // 列的最大下标
		while (i > 0 && j > 0) {
			if (path[i][j] == 1) {
				System.out.printf("第%d个商品放入到背包\n", i);
				j -= w[i-1];  // 减去当前物品重量 巧妙
			}
			i--;
		}
	}
	
}
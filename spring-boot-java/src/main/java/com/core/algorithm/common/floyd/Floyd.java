package com.core.algorithm.common.floyd;

import java.util.Arrays;

/**
 * 弗洛伊德算法-解决各个点到其它点最小路径问题
 * 
 */
public class Floyd {
	private static final int N = 65535;
	private char[] vertexs; // 顶点数组
	private int[][] distance; // 保存从各个顶点出发到其它顶点的[最短]距离
	private int[][] preVertexs; // 保存到达目标顶点的前驱顶点(记录中转路径)
	
	public Floyd(int[][] matrix, char[] vertexs) {
		this.vertexs = vertexs;
		this.distance = matrix;
		this.preVertexs = new int[vertexs.length][vertexs.length];
		for (int i = 0; i < vertexs.length; i++) {
			Arrays.fill(this.preVertexs[i], i);
		}
	}
	
	public void floyd() {
		int len = 0; // 顶点距离
		// 中间顶点遍历
		for (int k = 0; k < this.vertexs.length; k++) {
			// 边起始顶点
			for (int i = 0; i < this.vertexs.length; i++) {
				// 边终点顶点
				for (int j = 0; j < this.vertexs.length; j++) {
					// 下标i到下标j顶点距离 = 下标i到下标k顶点距离 + 下标k到下标j顶点距离
					len = distance[i][k] + distance[k][j];
					if (len < distance[i][j]) {
						distance[i][j] = len;
						preVertexs[i][j] = preVertexs[k][j]; 
					}
				}
			}
		}
	}
	
	public void show() {
		for (int i = 0; i < this.vertexs.length; i++) {
			System.out.println(Arrays.toString(distance[i]));
		}
		System.out.println();
		for (int i = 0; i < this.vertexs.length; i++) {
			System.out.println(Arrays.toString(preVertexs[i]));
		}
	}
	
	public static void main(String[] args) {
		char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
		int[][] matrix = {
					{ 0,  5,  7,  N,  N,  N,  2},
					{ 5,  0,  N,  9,  N,  N,  3},
					{ 7,  N,  0,  N,  8,  N,  N},
					{ N,  9,  N,  0,  N,  4,  N},
					{ N,  N,  8,  N,  0,  5,  4},
					{ N,  N,  N,  4,  5,  0,  6},
					{ 2,  3,  N,  N,  4,  6,  0}
				};
		Floyd floyd = new Floyd(matrix, vertexs);
		floyd.floyd();
		floyd.show();
	}
}
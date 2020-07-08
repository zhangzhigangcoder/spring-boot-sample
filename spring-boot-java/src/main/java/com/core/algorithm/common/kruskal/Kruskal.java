package com.core.algorithm.common.kruskal;

import java.util.Arrays;

/**
 * 克鲁斯卡尔算法-解决公交站问题
 *
 */
public class Kruskal {
	private int edgeNum; // 边的个数
	private char[] vertexs; // 顶点数组
	private int[][] matrix; // 邻接矩阵
	private static final int INF = Integer.MAX_VALUE;
	
	public Kruskal(char[] vertexs, int[][] matrix) {
		int vLen = vertexs.length;
		// 初始化顶点数组
		this.vertexs = new char[vLen];
		System.arraycopy(vertexs, 0, this.vertexs, 0, vLen);
		// 初始化邻接矩阵
		this.matrix = new int[vLen][vLen];
		for (int i = 0; i < vLen; i++) {
			System.arraycopy(matrix[i], 0, this.matrix[i], 0, vLen);
		}
		// 统计边
		for (int i = 0; i < vLen; i++) {
			for (int j = i + 1; j < vLen; j++) {
				if (this.matrix[i][j] != INF) {
					edgeNum++;
				}
			}
		}
	}
	
	/**
	 * 查找顶点下标，找不到返回-1
	 * @param ch
	 * @return
	 */
	private int getIndex(char v) {
		for (int i = 0; i < vertexs.length; i++) {
			if (vertexs[i] == v) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * 构造边和权值数组
	 * @return
	 */
	private Edge[] getEdges() {
		int index = 0;
		Edge[] edges = new Edge[edgeNum];
		for (int i = 0; i < this.vertexs.length; i++) {
			for (int j = i + 1; j < this.vertexs.length; j++) {
				if (matrix[i][j] != INF) {
					edges[index++] = new Edge(vertexs[i], vertexs[j], matrix[i][j]);
				}
			}
		}
		return edges;
	}
	
	/**
	 * 核心方法：
	 * 获取下标为i的顶点的终点，用于判断新添加的边是否会构成回路
	 * @param ends
	 * @param i
	 * @return
	 */
	private int getEnd(int[] ends, int i) {
		while (ends[i] != 0) {
			i = ends[i];
		}
		return i;
	}
	
	public void kruskal() {
		int index = 0;
		int[] ends = new int[edgeNum];
		
		Edge[] rets = new Edge[edgeNum]; // 保存最小生成树
		
		Edge[] edges = getEdges(); // 所有边
		
		Arrays.sort(edges); // 升序排序
		
		for (int i = 0; i < edgeNum; i++) {
			// 第i条边两顶点下标
			int sIdx = getIndex(edges[i].start);
			int eIdx = getIndex(edges[i].end);
			// 获取第i条边两顶点分别对应的终点
			int sEIdx = getEnd(ends, sIdx);
			int eEIdx = getEnd(ends, eIdx);
			// 如果终点不相等，说明不会形成回路
			if (sEIdx != eEIdx) {
				ends[sEIdx] = eEIdx;
				rets[index++] = edges[i];
			}
		}
		
//		for (Edge e : rets) {
//			if (null != e) {
//				System.out.println(e.start + " -> " + e.end + " : " + e.weight);
//			}
//		}
	}
	
	public void print() {
		for (int i = 0; i < this.vertexs.length; i++) {
			for (int j = 0; j < this.vertexs.length; j++) {
				System.out.printf("%12d", this.matrix[i][j]);
			}
			System.out.println();
		}
	}
	
	static class Edge implements Comparable<Edge> {
		char start; // 边的起始点
		char end;   // 边的终点
		int weight; // 边的权值
		
		public Edge(char start, char end, int weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
		}
		

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Edge [start=");
			builder.append(start);
			builder.append(", end=");
			builder.append(end);
			builder.append(", weight=");
			builder.append(weight);
			builder.append("]");
			return builder.toString();
		}

		
	}

	public static void main(String[] args) {
		char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
		int[][] matrix = {
					{ 0,   12,   INF,  INF,   INF,   16,   14  },
					{ 12,  0,    10,   INF,   INF,   7,    INF },
					{ INF, 10,   0,    3,     5,     6,    INF },
					{ INF, INF,  3,    0,     4,     INF,  INF },
					{ INF, INF,  5,    4,     0,     2,    8   },
					{ 16,  7,    6,    INF,   2,     0,    9   },
					{ 14,  INF,  INF,  INF,   8,     9,    0   }
				};
		Kruskal kruskal = new Kruskal(vertexs, matrix);
		kruskal.print();
		kruskal.kruskal();
	}
}
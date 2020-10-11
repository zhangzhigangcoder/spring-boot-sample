package com.algorithm.common.dijkstra;

import java.util.Arrays;

/**
 * 迪杰斯特拉算法-解决快递员送快递最小路径问题
 * 
 */
public class Dijkstra {
	private static final int N = 65535;
	private char[] vertexs; // 顶点数组
	private int[][] matrix; // 邻接矩阵
	private VisitedVertex vv; // 已经访问顶点集合
	
	public Dijkstra(char[] vertexs, int[][] matrix) {
		this.vertexs = vertexs;
		this.matrix = matrix;
	}
	
	public void dijkstra(int index) {
		this.vv = new VisitedVertex(this.vertexs.length, index);
		update(index);
		for (int i = 1; i < vertexs.length; i++) {
			update(vv.getNextVertex());
		}
		vv.show();
	}
	
	/**
	 * 更新下标为index顶点到其相邻联通顶点的距离并设置其为前驱顶点
	 * @param index
	 */
	private void update(int index) {
		int len = 0;
		for (int i = 0; i < matrix[index].length; i++) {
			// 出发顶点到下标为i顶点距离 = 出发顶点到下标为index顶点距离 + 下标为index顶点到下标为i顶点距离
			len = vv.getDistance(index) + matrix[index][i];
			if (!vv.visited(i) && len < vv.getDistance(i)) {
				vv.updateDistance(i, len); // 更新出发顶点到下标为i顶点距离
				vv.updatePreVertexIdx(i, index); // 更新下标为i顶点的前驱顶点为下标为index顶点
			}
		}
	}
	
	public void showGraph() {
		for (int[] link : this.matrix) {
			System.out.println(Arrays.toString(link));
		}
	}
	
	private static class VisitedVertex {
		private int[] already; // 记录各个顶点是否访问过 1：访问过 0：未访问
		private int[] preVertexIdx; // 记录前一个顶点下标，会动态更新
		private int[] distance; // 记录出发顶点到其它所有顶点距离，会动态更新
		
		/**
		 * @param length 顶点个数
		 * @param index 出发顶点下标
		 */
		public VisitedVertex(int length, int index) {
			this.already = new int[length];
			this.preVertexIdx = new int[length];
			this.distance = new int[length];
			Arrays.fill(distance, N);
			this.distance[index] = 0; // 设置出发顶点距离为0
			this.already[index] = 1; // 设置出发顶点已被访问
		}
		
		/**
		 * 判断下标为index的顶点是否被访问过
		 * @param index
		 * @return
		 */
		public boolean visited(int index) {
			return already[index] == 1;
		}
		
		/**
		 * 更新出发顶点到下标为index顶点的距离
		 * @param index
		 * @param distance
		 */
		public void updateDistance(int index, int distance) {
			this.distance[index] = distance;
		}
		
		/**
		 * 更新下标为preVertexIdx顶点的前驱顶点为下标index的顶点
		 * @param preVertexIdx
		 * @param index
		 */
		public void updatePreVertexIdx(int preVertexIdx, int index) {
			this.preVertexIdx[preVertexIdx] = index;
		}
		
		/**
		 * 返回出发顶点到下标为index顶点距离
		 * @param index
		 * @return
		 */
		public int getDistance(int index) {
			return this.distance[index];
		}
		
		/**
		 * 获取下一个访问顶点
		 * 广度优先算法
		 * @return
		 */
		public int getNextVertex() {
			int min = Integer.MAX_VALUE, index = 0;
			for (int i = 0; i < this.already.length; i++) {
				if (this.already[i] == 0 && this.distance[i] < min) {
					min = distance[i];
					index = i;
				}
			}
			this.already[index] = 1;
			return index;
		}
		
		public void show() {
			System.out.println("already=" + Arrays.toString(already));
			System.out.println("preVertexIdx=" + Arrays.toString(preVertexIdx));
			System.out.println("distance=" + Arrays.toString(distance));
		}
	}
	
	public static void main(String[] args) {
		char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
		int[][] matrix = {
					{ N,  5,  7,  N,  N,  N,  2},
					{ 5,  N,  N,  9,  N,  N,  3},
					{ 7,  N,  N,  N,  8,  N,  N},
					{ N,  9,  N,  N,  N,  4,  N},
					{ N,  N,  8,  N,  N,  5,  4},
					{ N,  N,  N,  4,  5,  N,  6},
					{ 2,  3,  N,  N,  4,  6,  N}
				};
		Dijkstra dijkstra = new Dijkstra(vertexs, matrix); 
//		dijkstra.showGraph();
		dijkstra.dijkstra(6);
	}
	
}
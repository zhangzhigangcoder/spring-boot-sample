package com.core.data.struct.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 图测试
 *
 */
public class Graph {
	private List<String> vertexList; // 顶点集合
	private int[][] edges; // 邻结矩阵
	private int numOfEdges; // 边的数量
	private boolean[] isVisited; // 顶点访问标记
	
	public Graph(int n) {
		edges = new int[n][n];
		isVisited = new boolean[n];
		vertexList = new ArrayList<String>();
	}
	
	/**
	 * 返回顶点个数
	 * @return
	 */
	public int getNumOfVertex() {
		return vertexList.size();
	}
	
	/**
	 * 返回边的数量
	 * @return
	 */
	public int getNumOfEdges() {
		return numOfEdges;
	}
	
	/**
	 * 返回指定位置(下标)的顶点值
	 * @param v
	 * @return
	 */
	public String getValByIndex(int v) {
		return vertexList.get(v);
	}
	
	/**
	 * 插入顶点
	 * @param vertex
	 */
	public void insertVertex(String vertex) {
		vertexList.add(vertex);
	}
	
	/**
	 * 添加边
	 * @param v1 顶点1下标
	 * @param v2 顶点2下标
	 * @param weight 1|0
	 */
	public void insertEdge(int v1, int v2, int weight) {
		edges[v1][v2] = weight;
		edges[v2][v1] = weight;
		numOfEdges++;
	}
	
	/**
	 * 获取指定顶点第一个邻接节点的下标
	 * @param v 顶点下标
	 * @return
	 */
	public int getFirstNeighbor(int v) {
		for (int i = v + 1; i < vertexList.size(); i++) {
			if (edges[v][i] == 1) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * 获取指定顶点的指定邻接点的下一个邻接点
	 * @param v1 顶点
	 * @param v2 v1顶点的v2邻接点
	 * @return
	 */
	public int getNextNeighbor(int v1, int v2) {
		for (int i = v2 + 1; i < vertexList.size(); i++) {
			if (edges[v1][i] == 1) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * 深度优先遍历算法
	 * @param isVisited
	 * @param v 顶点
	 */
	private void dfs(boolean[] isVisited, int v) {
		System.out.print(getValByIndex(v) + "->");
		// 标记当前顶点被访问过
		isVisited[v] = true;
		int w = getFirstNeighbor(v);
		while (-1 != w) {
			if (!isVisited[w]) {
				dfs(isVisited, w);
			}
			// 这里有一个回溯的过程
			w = getNextNeighbor(v, w);
		}
	}
	
	/**
	 * 遍历所有顶点
	 */
	public void dfs() {
		for (int i = 0; i < getNumOfVertex(); i++) {
			if (!isVisited[i]) {
				dfs(isVisited, i);
			}
		}
	}
	
	/**
	 * 打印图对应的矩阵
	 */
	public void showGraph() {
		for (int[] link : edges) {
			System.out.println(Arrays.toString(link));
		}
	}
	
	
	
	
}
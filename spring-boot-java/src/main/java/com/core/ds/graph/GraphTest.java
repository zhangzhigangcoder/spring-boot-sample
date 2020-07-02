package com.core.ds.graph;

/**
 * 图测试
 *
 */
public class GraphTest {
	public static void main(String[] args) {
		
//		String[] vertexs = {"A", "B", "C", "D", "E"};
		String[] vertexs = {"1", "2", "3", "4", "5", "6", "7", "8"};
		Graph graph = new Graph(vertexs.length);
		
		// 添加顶点
		for (String vertex : vertexs) {
			graph.insertVertex(vertex);
		}
		
		// 添加边
//		graph.insertEdge(0, 1, 1);
//		graph.insertEdge(0, 2, 1);
//		graph.insertEdge(1, 2, 1);
//		graph.insertEdge(1, 3, 1);
//		graph.insertEdge(1, 4, 1);
		
		graph.insertEdge(0, 1, 1);
		graph.insertEdge(0, 2, 1);
		graph.insertEdge(1, 3, 1);
		graph.insertEdge(1, 4, 1);
		graph.insertEdge(2, 5, 1);
		graph.insertEdge(2, 6, 1);
		graph.insertEdge(3, 7, 1);
		graph.insertEdge(4, 7, 1);
		graph.insertEdge(5, 6, 1);
		
		
		graph.showGraph();
		
		System.out.println("深度优先遍历：");
		graph.dfs();
		System.out.println();
		System.out.println("广度优先遍历：");
		graph.bfs();
	}
}
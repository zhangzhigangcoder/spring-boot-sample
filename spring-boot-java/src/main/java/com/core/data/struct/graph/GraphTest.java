package com.core.data.struct.graph;

/**
 * 图测试
 *
 */
public class GraphTest {
	public static void main(String[] args) {
		
		String[] vertexs = {"A", "B", "C", "D", "E"};
		Graph graph = new Graph(vertexs.length);
		
		// 添加顶点
		for (String vertex : vertexs) {
			graph.insertVertex(vertex);
		}
		
		// 添加边
		graph.insertEdge(0, 1, 1);
		graph.insertEdge(0, 2, 1);
		graph.insertEdge(1, 2, 1);
		graph.insertEdge(1, 3, 1);
		graph.insertEdge(1, 4, 1);
		
		graph.showGraph();
		
		System.out.println("深度遍历：");
		graph.dfs();
	}
}
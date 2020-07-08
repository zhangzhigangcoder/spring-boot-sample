package com.core.algorithm.common.prim;

/**
 * 普利姆算法-解决修路问题
 *
 */
public class Prim {

	public static void main(String[] args) {
		char[] data = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
		int verxs = data.length;
		int[][] weight = {
					{10000, 5, 7, 10000, 10000, 10000, 2},
					{5, 10000, 10000, 9, 10000, 10000, 3},
					{7, 10000, 10000, 10000, 8, 10000, 10000},
					{10000, 9, 10000, 10000, 10000, 4, 10000},
					{10000, 10000, 8, 10000, 10000, 5, 4},
					{10000, 10000, 10000, 4, 5, 10000, 6},
					{2, 3, 10000, 10000, 4, 6, 10000}
				};
		MGraph graph = new MGraph(verxs);
		
		MinTree minTree = new MinTree();
		minTree.createGraph(graph, verxs, data, weight);
		
		minTree.showGraph(graph);
		
		minTree.prim(graph, 0);
	}
	
	static class MinTree {
		
		public void createGraph(MGraph graph, int verxs, char data[], int[][] weight) {
			int i, j;
			for (i = 0; i < verxs; i++) {
				graph.data[i] = data[i];
				for (j = 0; j < verxs; j++) {
					graph.weight[i][j] = weight[i][j]; 
				}
			}
		}
		
		public void showGraph(MGraph graph) {
			for (int i = 0; i < graph.verxs; i++) {
				for (int j = 0; j < graph.verxs; j++) {
					System.out.printf("%12d", graph.weight[i][j]);
				}
				System.out.println();
			}
		}
		
		/**
		 * 用普利姆算法生成最小树
		 * @param graph
		 * @param v
		 */
		public void prim(MGraph graph, int v) {
			int visited[] = new int[graph.verxs]; // 标记顶点是否访问过
			visited[v] = 1;
			int h1 = -1, h2 = -1, minWeight = 10000;
			// 因为有graph.verxs个顶点，普利姆算法结束后，会有graph.verxs-1个边
			for (int k = 1; k < graph.verxs; k++) {
				// 遍历整个图，查找符合条件的顶点和边
				for (int i = 0; i < graph.verxs; i++) {
					for (int j = 0; j < graph.verxs; j++) {
						if (visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] < minWeight) {
							minWeight = graph.weight[i][j];
							h1 = i;
							h2 = j;
						}
					}
				}
				System.out.println("边<" + graph.data[h1] + "," + graph.data[h2] + "> 权值：" + minWeight);
				visited[h2] = 1;
				minWeight = 10000;
			}
		}
	}

	static class MGraph {
		int verxs; // 表示图节点个数
		char[] data; // 存放节点数据
		int[][] weight; // 存放边(邻接矩阵)
		public MGraph(int verxs) {
			this.verxs = verxs;
			data = new char[verxs];
			weight = new int[verxs][verxs];
		}
	}
}
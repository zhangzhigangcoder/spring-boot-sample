package com.algorithm.common.prim;

/**
 * 普利姆算法-解决修路问题
 *
 */
public class Prim {
	
	private int vlen; // 顶点个数
	private char[] vertexs; // 存放顶点数据
	private int[][] edges; // 存放边(邻接矩阵)
	
	private Prim() {}
	
	public static Prim build(char vertexs[], int[][] edges) {
		Prim prim = new Prim();
		prim.vlen = vertexs.length;
		prim.vertexs = new char[prim.vlen];
		prim.edges = new int[prim.vlen][prim.vlen];
		// 采用复制而不是引用传递，避免原始数据被修改
		for (int i = 0; i < prim.vlen; i++) {
			prim.vertexs[i] = vertexs[i];
			for (int j = 0; j < prim.vlen; j++) {
				prim.edges[i][j] = edges[i][j]; 
			}
		}
		return prim;
	}
	
	public void showGraph() {
		for (int i = 0; i < this.vlen; i++) {
			for (int j = 0; j < this.vlen; j++) {
				System.out.printf("%12d", this.edges[i][j]);
			}
			System.out.println();
		}
	}
	
	/**
	 * 构建最小联通图
	 * @param startVetex 起始顶点，不同的起始顶点，最后构建的连通图都是一样的
	 */
	public void prim(int startVetex) {
		int visited[] = new int[this.vlen]; // 标记顶点是否访问过
		visited[startVetex] = 1;
		int h1 = -1, h2 = -1, minWeight = 10000;
		// 因为有graph.verxs个顶点，普利姆算法结束后，会有graph.verxs-1个边
		for (int k = 1; k < this.vlen; k++) {
			// 遍历整个图，以访问过的顶点为起点，找到下一个联通且权值最小的边
			for (int i = 0; i < this.vlen; i++) {
				for (int j = 0; j < this.vlen && visited[i] == 1; j++) {
					// 这里也体现了贪心算法的思想
					if (visited[j] == 0 && this.edges[i][j] < minWeight) {
						minWeight = this.edges[i][j];
						h1 = i;
						h2 = j;
					}
				}
			}
			System.out.println("边<" + this.vertexs[h1] + "," + this.vertexs[h2] + "> 权值：" + minWeight);
			visited[h2] = 1;
			minWeight = 10000;
		}
	}
	
	public static void main(String[] args) {
		char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
		int[][] edges = {
					{10000, 5, 7, 10000, 10000, 10000, 2},
					{5, 10000, 10000, 9, 10000, 10000, 3},
					{7, 10000, 10000, 10000, 8, 10000, 10000},
					{10000, 9, 10000, 10000, 10000, 4, 10000},
					{10000, 10000, 8, 10000, 10000, 5, 4},
					{10000, 10000, 10000, 4, 5, 10000, 6},
					{2, 3, 10000, 10000, 4, 6, 10000}
				};
		Prim prim = Prim.build(vertexs, edges);
		prim.showGraph();
		prim.prim(0);
	}
}
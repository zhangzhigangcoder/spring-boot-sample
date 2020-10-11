package com.ds.recursion;

/**
 * 使用递归解决迷宫问题
 *
 */
public class Maze {

	public static void main(String[] args) {
		int rows = 7, cols = 6;
		int[][] map = initMap(rows, cols);
		System.out.println("地图详情：");
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		long start = System.currentTimeMillis();
		boolean result = findWay(map, 1, 1, rows - 2, cols - 2);
		System.out.println(result);
		System.out.println(System.currentTimeMillis() - start);
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	/**
	 * 走迷宫
	 * 游戏规则：
	 * 1.  起始点(i,j)为(1,1)
	 * 2. 如果能走到(rows -2, cols -2),就算成功
	 * 3.对map[i][j]进行约定：
	 * 		0：没有走过
	 * 		1：墙
	 * 		2：已经走过，是通路
	 * 		3：已经走过，但是走不通 
	 * 4. 查找策略： 下 -> 右 -> 上 -> 左
	 * @param map 地图
	 * @param (startRow, startCol) 起始点
	 * @param (endRow, endCol) 出口
	 * @return
	 */
	public static boolean findWay(int[][] map, int startRow, int startCol, int endRow, int endCol) {
		if (map[endRow][endCol] == 2) { // 已经找到
			return true;
		} else {
			if (map[startRow][startCol] == 0) { // 该点还没有走过
				map[startRow][startCol] = 2; // 假定该点是可以走通的
				if (findWay(map, startRow + 1, startCol, endRow, endCol)) { // 向下走
					return true;
				} else if (findWay(map, startRow, startCol + 1, endRow, endCol)) { // 向右走
					return true;
				} else if (findWay(map, startRow - 1, startCol, endRow, endCol)) { // 向上走
					return true;
				} else if (findWay(map, startRow, startCol - 1, endRow, endCol)) { // 向左走
					return true;
				} else {
					// 4个方向都没有走通，将该点标记为3
					map[startRow][startCol] = 3;
					return false;
				}
			} else { // 如果map[startRow][startCol] != 0, 可能是 1, 2, 3
				return false;
			}
		}
	}
	
	/**
	 * 初始化地图
	 * @param rows 行数
	 * @param cols 列数
	 * @return
	 */
	public static int[][] initMap(int rows, int cols) {
		int[][] map = new int[rows][cols];
		// 上下全部置为1
		for (int i = 0; i < cols; i++) {
			map[0][i] = 1;
			map[rows-1][i] = 1;
		}
		
		// 左右全部置为1
		for (int i = 0; i < rows; i++) {
			map[i][0] = 1;
			map[i][cols-1] = 1;
		}
		
		// 生成挡板
		map[5][1] = 1;
		map[3][2] = 1;
		map[4][3] = 1;
		map[5][2] = 1;
		return map;
	}
}
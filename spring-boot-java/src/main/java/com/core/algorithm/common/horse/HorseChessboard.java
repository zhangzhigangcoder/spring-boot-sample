package com.core.algorithm.common.horse;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 马踏棋盘算法
 * 
 */
public class HorseChessboard {
	private int rows; // 棋盘行数
	private int cols; // 棋盘列数
	private int[][] chessboard; // 棋盘
	private boolean visited[]; // 标记棋盘的各个位置是否被访问过
	private boolean finished; // 标记棋盘的所有位置是否都被访问过
	private Point startPoint; // 起始位置
	private boolean isCountFromStart; // 是否从起始位置开始计数(不从起始位置计数，要求终点是起始点)
	
	public HorseChessboard(int rows, int cols, Point start, boolean isCountFromStart) {
		this.rows = rows;
		this.cols = cols;
		this.startPoint = start;
		this.isCountFromStart = isCountFromStart;
		this.chessboard = new int[this.rows][this.cols];
		this.visited = new boolean[this.rows * this.cols];
	}
	
	/**
	 * 获取当前位置下一步可以走的位置集合
	 * @param x
	 * @param y
	 * @return
	 */
	private List<Point> next(int x, int y) {
		List<Point> pList = new ArrayList<>();
		int tempX, tempY;
		
		// 上
		if ((tempY = y - 2) >= 0) {
			if ((tempX = x - 1) >= 0) {
				pList.add(new Point(tempX, tempY));
			}
			if ((tempX = x + 1) < cols) {
				pList.add(new Point(tempX, tempY));
			}
		}
		
		// 下
		if ((tempY = y + 2) < rows) {
			if ((tempX = x - 1) >= 0) {
				pList.add(new Point(tempX, tempY));
			}
			if ((tempX = x + 1) < cols) {
				pList.add(new Point(tempX, tempY));
			}
		}
		
		// 左
		if ((tempX = x - 2) >= 0) {
			if ((tempY = y - 1) >= 0) {
				pList.add(new Point(tempX, tempY));
			}
			if ((tempY = y + 1) < rows) {
				pList.add(new Point(tempX, tempY));
			}
		}
		
		// 右
		if ((tempX = x + 2) < cols) {
			if ((tempY = y - 1) >= 0) {
				pList.add(new Point(tempX, tempY));
			}
			if ((tempY = y + 1) < rows) {
				pList.add(new Point(tempX, tempY));
			}
		}
		return pList;
	}
	
	public void traversalChessboard() {
		int step = isCountFromStart ? 1 : 0;
		traversalChessboard(startPoint.y, startPoint.x, step);
	}
	
	private void traversalChessboard(int row, int column, int step) {
		if (isCountFromStart || row != startPoint.y || column != startPoint.x || step == rows * cols) {
			// 标记该位置是第几步
			chessboard[row][column] = step; 
			// 标记改位置已经访问过
			visited[row * cols + column] = true; 
		}
		List<Point> nextPoints = next(column, row);
		// 贪婪算法
		sort(nextPoints);
		// 遍历所有可走位置
		while(!nextPoints.isEmpty() && !finished) {
			Point p = nextPoints.remove(0); 
			if (!visited[p.y * cols + p.x] && (isCountFromStart ||  (step == rows * cols - 1 || !p.equals(startPoint)))) {
				traversalChessboard(p.y, p.x, step + 1);
			}
		}
		if (!finished) {
			if (step == rows * cols && (isCountFromStart || row == startPoint.y && column == startPoint.x)) {
				finished = true;
			} else {
				// 重置状态， 便于回溯
				chessboard[row][column] = 0;
				visited[row * cols + column] = false;
			}
		}
	}
	
	private void sort(List<Point> ps) {
		ps.sort(new Comparator<Point>() {
			@Override
			public int compare(Point o1, Point o2) {
				return next(o1.x, o1.y).size() - next(o2.x, o2.y).size();
			}
		});
	}
	
	public void showChessboard() {
		for (int[] rows : chessboard) {
			System.out.println(Arrays.toString(rows));
		}
	}
	
	public static void main(String[] args) {
		int row = 1, column = 6;
		long start = System.currentTimeMillis();
		HorseChessboard horseChessboard = new HorseChessboard(8, 8, new Point(column-1, row-1), true);
		horseChessboard.traversalChessboard();
		horseChessboard.showChessboard();
		System.out.println(System.currentTimeMillis() - start);
	}
}
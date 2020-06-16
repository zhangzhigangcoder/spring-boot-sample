package com.core.ds.recursion;

/**
 * 递归
 *
 */
public class RecursionTest {

	/**
	 * 阶乘问题
	 * @param n
	 * @return
	 */
	public static int factorial(int n) {
		if (n == 1) {
			return 1;
		} else {
			return factorial(n - 1) * n;
		}
	}
}
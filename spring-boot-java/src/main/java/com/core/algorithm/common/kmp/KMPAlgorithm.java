package com.core.algorithm.common.kmp;

import java.util.Arrays;

/**
 * KMP算法匹配
 *参考：https://blog.csdn.net/v_JULY_v/article/details/7041827
 */
public class KMPAlgorithm {

	public static void main(String[] args) {
		String str1 = "BBC ABCDAB ABCDABCDABDE";
		String str2 = "ABCDABD";
		int[] match = matchTab("ABCDABD");
		System.out.println(Arrays.toString(match));
		System.out.println(search(str1, str2, match));
	}

	/**
	 * 查询
	 * @param oriStr 原串
	 * @param subStr 子串
	 * @param match 部分匹配表
	 * @return
	 */
	public static int search(String oriStr,String subStr, int[] match) {
		// i原串下标， j子串下标
		for (int i = 0, j = 0; i < oriStr.length(); i++) {
			// 核心
			while (j > 0 && oriStr.charAt(i) != subStr.charAt(j)) {
				// j = match[j-1] + (j-match[j-1])
				// j向前移动了(j-match[j-1])个字符，相当于子串向右移动了(j-match[j-1])个字符
				j = match[j-1]; 
			}
			if (oriStr.charAt(i) == subStr.charAt(j)) {
				j++;
			}
			if (j == subStr.length()) {
				return i - j + 1;
			}
		}
		return -1;
	}
	
	/**
	 * 生成部分匹配表
	 * @param dest
	 * @return
	 */
	public static int[] matchTab(String dest) {
		int[] match = new int[dest.length()];
		match[0] = 0;
		// i为后缀下标 j为前缀下标
		for (int i = 1, j = 0; i < dest.length(); i++) {
			System.out.print("i=" + i + " j=" + j + " ");
			System.out.print(dest.charAt(i) + "=>" + dest.charAt(j));
			// 核心
			// 利用前面生成的部分匹配表，可能会减少匹配次数
			while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
				// j = match[j-1] + (j-match[j-1])
				// j向前移动了(j-match[j-1])个字符，相当于子串向右移动了(j-match[j-1])个字符
				j = match[j-1];
			}
			
			if (dest.charAt(i) == dest.charAt(j)) {
				j++;
			}
			match[i] = j;
			System.out.println("=>" + j);
		}
		return match;
	}
}
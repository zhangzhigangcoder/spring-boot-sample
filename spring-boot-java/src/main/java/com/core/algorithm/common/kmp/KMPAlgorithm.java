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

	public static int search(String oriStr,String subStr, int[] next) {
		for (int i = 0, j = 0; i < oriStr.length(); i++) {
			// 核心
			while (j > 0 && oriStr.charAt(i) != subStr.charAt(j)) {
				// j = next[j-1] + (j-next[j-1])
				// 相当于字串向右移动了(j-next[j-1])个字符
				j = next[j-1]; 
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
	public static int[] matchTab(String dest) {
		int[] match = new int[dest.length()];
		match[0] = 0;
		for (int i = 1, j = 0; i < dest.length(); i++) {
			System.out.print("i=" + i + " j=" + j + " ");
			System.out.print(dest.charAt(i) + "=>" + dest.charAt(j));
			// 核心
			while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
				// j = next[j-1] + (j-next[j-1])
				// 相当于字串向右移动了(j-next[j-1])个字符
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
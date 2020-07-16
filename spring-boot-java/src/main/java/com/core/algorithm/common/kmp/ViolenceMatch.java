package com.core.algorithm.common.kmp;

/**
 * 暴力匹配
 *
 */
public class ViolenceMatch {

	public static void main(String[] args) {
		String str1 = "硅硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你好";
		String str2 = "尚硅谷你尚硅你";
		System.out.println(violenceMatch(str1, str2));
	}

	public static int violenceMatch(String str, String subStr) {
		char[] s1 = str.toCharArray();
		char[] s2 = subStr.toCharArray();
		int len1 = s1.length, len2 = s2.length;
		int i = 0, j = 0;
		while (i < len1 && j < len2) {
			if (s1[i] == s2[j]) { // 字符匹配成功，i和j都自增
				i++;
				j++;
			} else { // 匹配失败，i移动到下一位，j置0
				i = i - (j - 1);
				j = 0;
			}
		}
		if (j == len2) { // 匹配长度等于子串长度，说明匹配成功，返回起始下标
			return i - j;
		}
		return -1;
	}

}
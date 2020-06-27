package com.core.algorithm.appliance.huffmancode;

import java.util.Arrays;

/**
 * 赫夫曼编码   
 * @author zhangzhigang
 *
 */
public class HuffmanCodeTest {
	public static void main(String[] args) {
		String str = "i like like like java do you like a java ";
		// 101010011010001111010011010001111010011010001111011000111011011111001110001001011101010011100101001101000111101100011101101111101
		System.out.println(Arrays.toString(str.getBytes()));
		byte[] encodeBytes = HuffmanCode.encode(str.getBytes());
		System.out.println(Arrays.toString(encodeBytes));
		byte[] decodeBytes = HuffmanCode.decode(encodeBytes);
		System.out.println(new String(decodeBytes));
	}
	
	public static void main2(String[] args) {
		byte b = (byte) Integer.parseInt("01", 2);
//		System.out.println(Short.parseShort("10101001", 2));
		System.out.println(b);
		System.out.println(Integer.toString(b));
//		System.out.println(Integer.toBinaryString(169));
	}
}
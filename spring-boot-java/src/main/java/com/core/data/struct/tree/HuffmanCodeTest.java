package com.core.data.struct.tree;

/**
 * 赫夫曼编码测试  
 * @author zhangzhigang
 *
 */
public class HuffmanCodeTest {
	public static void main(String[] args) {
		String str = "i like like like java do you like a java ";
		// 101010011010001111010011010001111010011010001111011000111011011111001110001001011101010011100101001101000111101100011101101111101
//		System.out.println(Arrays.toString(str.getBytes()));
//		byte[] encodeBytes = HuffmanCode.encode(str.getBytes());
//		System.out.println(Arrays.toString(encodeBytes));
//		byte[] decodeBytes = HuffmanCode.decode(encodeBytes);
//		System.out.println(new String(decodeBytes));
		// C:\Users\qw\Pictures\1.jpg
		String srcFile = "/Users/zhangzhigang/Desktop/Effective Java（中文版第3版）.pdf";
		String dstFile = "/Users/zhangzhigang/Desktop/5.zip";
		String dstFile2 = "/Users/zhangzhigang/Desktop/55.pdf";
		HuffmanCode.zipFile(srcFile, dstFile);
//		HuffmanCode.unZipFile(dstFile, dstFile2);
	}
	
}
package com.core.data.struct.tree;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 赫夫曼树应用-赫夫曼编码
 *
 */
public class HuffmanCode {

	private static Map<Byte, String> huffmanCodes = new HashMap<Byte, String>();

	/**
	 * 压缩文件
	 * @param srcFile 源文件路径
	 * @param dstFile 目标文件路径
	 */
	public static void zipFile(String srcFile, String dstFile) {
		OutputStream os = null;
		ObjectOutputStream oos = null;
		FileInputStream is = null;
		try {
			// 获取输入流，并读取文件内容
			is = new FileInputStream(srcFile);
			byte[] srcBytes = new byte[is.available()];
			is.read(srcBytes);
			// 编码
			byte[] huffmanBytes = encode(srcBytes);
			// 获取输出流，并写出编码数据和编码表
			os = new FileOutputStream(dstFile);
			oos = new ObjectOutputStream(os);
			oos.writeObject(huffmanBytes);
			oos.writeObject(huffmanCodes);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != oos) {
					oos.close();
				}
				if (null != os) {
					os.close();
				}
				if (null != is) {
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 解压文件
	 * @param srcFile 
	 * @param dstFile
	 */
	@SuppressWarnings("unchecked")
	public static void unZipFile(String srcFile, String dstFile) {
		InputStream is = null;
		ObjectInputStream ois = null;
		OutputStream os = null;
		try {
			// 获取输入流
			is = new FileInputStream(srcFile);
			ois = new ObjectInputStream(is);
			// 读取文件内容
			// 编码子节数组
			byte[] encodeBytes = (byte[]) ois.readObject();
			// 编码表
			Map<Byte, String> huffmanCodes = (Map<Byte, String>) ois.readObject();
			HuffmanCode.huffmanCodes = huffmanCodes;
			// 解码
			byte[] decodeBytes = decode(encodeBytes);
			// 创建输出流，并写出数据
			os = new FileOutputStream(dstFile);
			os.write(decodeBytes);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != os) {
					os.close();
				}
				if (null != ois) {
					ois.close();
				}
				if (null != is) {
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 将字节数组用赫夫曼编码编码成赫夫曼编码字节数组
	 * 
	 * @param originalBytes 待编码字节数组
	 */
	public static byte[] encode(byte[] originalBytes) {
		// 生成编码表
		generateHuffmanCodes(originalBytes);

		StringBuilder sb = new StringBuilder();
		for (byte b : originalBytes) {
			sb.append(huffmanCodes.get(b));
		}
		int len = (sb.length() + 7) / 8;
		byte[] huffmanCodeBytes = new byte[len + 1];
		int index = 0;
		for (int i = 0; i < sb.length(); i += 8) {
			String strByte;
			if (i + 8 > sb.length()) { // 不够8位
				strByte = sb.substring(i);
				// 最后一个数组元素不是有效数组，表示最后一个有效元素占几位
				huffmanCodeBytes[len] = (byte) strByte.length();
			} else {
				strByte = sb.substring(i, i + 8);
			}
			huffmanCodeBytes[index++] = (byte) Integer.parseInt(strByte, 2);
		}
		return huffmanCodeBytes;
	}

	/**
	 * 将赫夫曼编码的二进制数组解码为二进制数组
	 * 
	 * @param huffmanBytes 编码过的子节数组
	 * @return
	 */
	public static byte[] decode(byte[] huffmanBytes) {
		// 将赫夫曼编码的字节数组转换成二进制字符串
		StringBuilder strBuilder = new StringBuilder();
		for (int i = 0; i < huffmanBytes.length - 1; i++) {
			// 此处有特殊处理，防止最后一个子节有问题
			boolean isLast = i == huffmanBytes.length - 2;
			strBuilder.append(byteToBitString(!isLast, huffmanBytes[i], huffmanBytes[huffmanBytes.length - 1]));
		}
		// 将赫夫曼编码表键值互换
		Map<String, Byte> map = new HashMap<>();
		for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
			map.put(entry.getValue(), entry.getKey());
		}

		// 将赫夫曼编码的字节数组进行解码
		List<Byte> list = new ArrayList<>();
		for (int i = 0; i < strBuilder.length();) {
			int count = 1;
			boolean flag = true;
			Byte b = null;
			while (flag) {
				String key = strBuilder.substring(i, i + count);
				b = map.get(key);
				if (null == b) {
					count++;
				} else {
					flag = false;
				}
			}
			list.add(b);
			i += count;
		}
		// 将解码后的字节列表转换为字节数组
		byte[] b = new byte[list.size()];
		for (int i = 0; i < b.length; i++) {
			b[i] = list.get(i);
		}
		return b;
	}

	/**
	 * 根据待编码字节数组生成赫夫曼编码表
	 * 
	 * @param bytes
	 * @return
	 */
	private static Map<Byte, String> generateHuffmanCodes(byte[] bytes) {
		List<Node> nodes = HuffmanCode.getNodes(bytes);
		buildCodeRoutes(createHuffmanTree(nodes), "", new StringBuilder());
		return huffmanCodes;
	}

	/**
	 * 将字节转换成字符串，位数不足，高位补0
	 * 
	 * @param isComplete 是否要补足8位
	 * @param b
	 * @param count      表示最后一个有效元素占几位
	 * @return
	 */
	private static String byteToBitString(boolean isComplete, byte b, byte count) {
		int temp = b;
		temp |= 256;
		String str = Integer.toBinaryString(temp);
		str = str.substring(str.length() - 8);
		if (!isComplete) {
			str = str.substring(str.length() - count);
		}
		return str;
	}

	/**
	 * 创建霍夫曼树
	 * 
	 * @param arr
	 * @return
	 */
	private static Node createHuffmanTree(List<Node> nodes) {
		// 1. 将集合中节点按照霍夫曼规则创建成霍夫曼树
		while (nodes.size() > 1) {
			// 1.1 从小到大排序
			Collections.sort(nodes);
			// 1.2 将最小两个节点作为子节点构成一颗新的二叉树，父节点权值为子节点权值之和
			Node left = nodes.get(0);
			Node right = nodes.get(1);
			Node parent = new Node(null, left.weight + right.weight);
			parent.left = left;
			parent.right = right;

			// 1.3 移除两个子节点，并将新的根节点加入集合中，最终集合中只会剩下一个根节点
			nodes.remove(left);
			nodes.remove(right);
			nodes.add(parent);
		}
		return nodes.get(0);
	}

	/**
	 * 构建节点路径
	 * 
	 * @param node            起始节点
	 * @param code            路径值 左-"0" 右-"1"
	 * @param parentNodeRoute 父节点路径
	 */
	private static void buildCodeRoutes(Node node, String code, StringBuilder parentNodeRoute) {
		// 注意这里，必须创建新对象，保存当前节点路径
		StringBuilder temp = new StringBuilder(parentNodeRoute);
		temp.append(code);
		if (null != node) {
			if (null == node.data) { // 非叶子节点
				// 向左递归
				buildCodeRoutes(node.left, "0", temp);
				// 向右递归
				buildCodeRoutes(node.right, "1", temp);
				return;
			}
			huffmanCodes.put(node.data, temp.toString());
		}
	}

	private static List<Node> getNodes(byte[] bytes) {
		List<Node> nodes = new ArrayList<>();

		Map<Byte, Integer> counts = new HashMap<Byte, Integer>();
		for (Byte b : bytes) {
			Integer count = counts.get(b);
			if (null == count) {
				count = 0;
			}
			counts.put(b, count + 1);
		}
		for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
			nodes.add(new Node(entry.getKey(), entry.getValue()));
		}
		return nodes;
	}

	/**
	 * 前序遍历
	 * 
	 * @param root
	 */
	public static void preOrder(Node root) {
		if (null == root) {
			System.out.println("是空树，不能遍历");
			return;
		}
		root.preOrder();
	}

	private static class Node implements Comparable<Node> {
		private Byte data; // 节点数值
		private int weight; // 节点权值
		private Node left; // 左子节点
		private Node right; // 右子节点

		public Node(Byte data, int weight) {
			this.data = data;
			this.weight = weight;
		}

		public void preOrder() {
			System.out.println(this);
			if (null != this.left) {
				this.left.preOrder();
			}
			if (null != this.right) {
				this.right.preOrder();
			}
		}

		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Node [data=");
			builder.append(data);
			builder.append(", weight=");
			builder.append(weight);
			builder.append("]");
			return builder.toString();
		}
	}
}
package com.jvm.memory;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 内存溢出  Eclipse Memory Analyzer tool(MAT)
 * -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * 
 * @author zhangzhigang
 *
 */
public class HeapOOM {
	
	public static void main(String[] args) {
		List<OOMObject> list = new ArrayList<>();
		while(true) {
			list.add(new OOMObject());
		}
	}
	
	
	static class OOMObject {
	}
}

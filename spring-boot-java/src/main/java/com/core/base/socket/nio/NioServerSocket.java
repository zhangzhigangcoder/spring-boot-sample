package com.core.base.socket.nio;

import java.nio.ByteBuffer;

/**
 * 
 * @author zhangzhigang
 */
public class NioServerSocket {
	public static void main(String[] args) {
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		buffer.clear();
		buffer.compact();
	}
    
}
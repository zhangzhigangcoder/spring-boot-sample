package org.spring.boot.pool;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class Main {

	public static void main(String[] args) throws Exception {
		ConnectionFactory connectFactory = new ConnectionFactory();
		GenericObjectPoolConfig config = new GenericObjectPoolConfig();
		config.setMaxTotal(5);
		// 设置获取连接超时时间
		config.setMaxWaitMillis(1000);
		ConnectionPool connectionPool = new ConnectionPool(connectFactory, config);
		for (int i = 0; i < 7; i++) {
			Connection conn = connectionPool.borrowObject();
			System.out.println("brrow a connect: " + conn + " active connection: " + connectionPool.getNumActive());
			connectionPool.returnObject(conn);
		}
		connectionPool.close();
	}
}

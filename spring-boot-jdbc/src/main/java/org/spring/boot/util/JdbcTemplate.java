package org.spring.boot.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.spring.boot.config.JdbcConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JdbcTemplate {

	public static final List<Connection> connections = new ArrayList<>();

	@Autowired
	private JdbcConfigProperties jdbcConfigProperties;

	private Lock lock = new ReentrantLock();

	/**
	 * 获取数据库连接
	 * 
	 * @return Connection
	 */
	private Connection getConnection() {
		if (!connections.isEmpty()) {
			return connections.get(0);
		}
		try {
			lock.lock();
			if (connections.isEmpty()) {
				Connection conn = DriverManager.getConnection(jdbcConfigProperties.getUrl(),
						jdbcConfigProperties.getUsername(), jdbcConfigProperties.getPassword());
				connections.add(conn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		return connections.get(0);
	}
	
	public void add(String sql) {
		try {
			Connection conn = getConnection();
			conn.setAutoCommit(false);
			Statement s = conn.createStatement();
			boolean result = s.execute(sql);
			if (result) {
				System.out.println("数据新增成功");
			}
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(String sql) {
		try {
			Connection conn = getConnection();
			conn.setAutoCommit(false);
			Statement s = conn.createStatement();
			boolean result = s.execute(sql);
			conn.commit();
			if (result) {
				System.out.println("数据删除成功");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(String sql) {
		Connection conn = getConnection();
		try {
			Statement s = conn.createStatement();
			conn.setAutoCommit(false);
			boolean result = s.execute(sql);
			conn.commit();
			if (result) {
				System.out.println("数据更新成功");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public ResultSet query(String sql) {
		try {
			Connection conn = getConnection();
			Statement s = conn.createStatement();
			return s.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int queryCount(String sql) {
		try {
			Connection conn = getConnection();
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}

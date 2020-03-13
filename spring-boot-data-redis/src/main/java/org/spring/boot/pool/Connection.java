package org.spring.boot.pool;

public class Connection {

	private int id;
	
	public Connection(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Connection [id=" + id + "]";
	}
}

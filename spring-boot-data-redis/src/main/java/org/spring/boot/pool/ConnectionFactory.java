package org.spring.boot.pool;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

public class ConnectionFactory extends BasePooledObjectFactory<Connection> {

	private AtomicInteger idCount = new AtomicInteger(1);
	
	@Override
	public Connection create() throws Exception {
		return new Connection(idCount.getAndAdd(1));
	}

	@Override
	public PooledObject<Connection> wrap(Connection conn) {
		return new DefaultPooledObject<Connection>(conn);
	}

}

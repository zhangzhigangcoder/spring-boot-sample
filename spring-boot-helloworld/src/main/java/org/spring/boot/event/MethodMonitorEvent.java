package org.spring.boot.event;

import java.util.EventObject;

public class MethodMonitorEvent extends EventObject {

	private static final long serialVersionUID = 8854536760710490770L;
	
	private long timestamp;

	public MethodMonitorEvent(Object source) {
		super(source);
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
}

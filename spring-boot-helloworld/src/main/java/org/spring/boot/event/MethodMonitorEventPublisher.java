package org.spring.boot.event;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MethodMonitorEventPublisher {
	private List<MethodMonitorEventListener> listeners = new ArrayList<>();
	
	public void methodMonitor() throws InterruptedException {
		MethodMonitorEvent event = new MethodMonitorEvent(this);
		publishEvent("begin", event);
		TimeUnit.SECONDS.sleep(5);
		publishEvent("end", event);
	}
	
	private void publishEvent(String status, MethodMonitorEvent event) {
		List<MethodMonitorEventListener> copyListeners = new ArrayList<>(listeners);
		copyListeners.forEach(p -> {
			if ("begin".equals(status)) {
				p.onMethodBegin(event);
			} else {
				p.onMethodEnd(event);
			}
		});
	}
	
	public void addEventListener(MethodMonitorEventListener listener) {
		listeners.add(listener);
	}
	
	public static void main(String[] args) throws InterruptedException {
		MethodMonitorEventPublisher publisher = new MethodMonitorEventPublisher();
		publisher.addEventListener(new MethodMonitorEventListener());
		publisher.methodMonitor();
	}
	
}

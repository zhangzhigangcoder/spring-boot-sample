package org.spring.boot.event;

import java.util.EventListener;

public class MethodMonitorEventListener implements EventListener {

	/**
	 * 记录方法开始执行时间
	 * @param event
	 */
	public void onMethodBegin(MethodMonitorEvent event) {
		System.out.println("--方法开始执行...");
		event.setTimestamp(System.currentTimeMillis());
	}
	
	/**
	 * 计算方法执行耗时
	 * @param event
	 */
	public void onMethodEnd(MethodMonitorEvent event) {
		long duration = System.currentTimeMillis() - event.getTimestamp();
		System.out.println("--方法执行结束");
		System.out.println("--方法耗时：" + duration);
	}
}

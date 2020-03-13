package org.spring.boot.event.spring.application;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.SmartApplicationListener;

/**
 * 王五监听器-有序
 *
 */
@Configuration
public class WangwuListener implements SmartApplicationListener {

	/**
	 * 判断事件类型
	 */
	@Override
	public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
		return eventType == ContentEvent.class;
	}

	/**
	 * 判断事件源类型
	 */
	@Override
	public boolean supportsSourceType(Class<?> sourceType) {
		return sourceType == String.class;
	}
	
	@Override
	public int getOrder() {
		return 1;
	}

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		System.out.println("王五收到了新的内容：" + event.getSource());
	}

}

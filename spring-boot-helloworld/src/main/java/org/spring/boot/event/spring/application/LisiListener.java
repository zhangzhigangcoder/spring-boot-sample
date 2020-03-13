package org.spring.boot.event.spring.application;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;

/**
 * 李四监听器-无序
 *
 */
@Configuration
public class LisiListener implements ApplicationListener<ApplicationEvent> {

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		// 需要判断事件类型
		if (event instanceof ContentEvent) {
			System.out.println("李四收到了新的内容：" + event.getSource());
		}
	}

	
}

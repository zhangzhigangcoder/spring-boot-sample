package org.spring.boot.event.spring.application;

import org.springframework.context.ApplicationEvent;

/**
 * 自定义事件
 *
 */
public class ContentEvent extends ApplicationEvent {
	private static final long serialVersionUID = 315512208368323957L;

	public ContentEvent(String content) {
		super(content);
	}

	/** 
	 * **************** spring提供ApplicationEvent ********************
	 * 
	 * ApplicationEvent
	 * 
	 * ** ApplicationContextEvent
	 * 
	 * **** ContextStartedEvent
	 * 
	 * **** ContextRefreshedEvent
	 * 
	 * **** ContextClosedEvent
	 * 
	 * **** ContextStoppedEvent
	 * 
	 * ** SpringApplicationEvent
	 * 
	 * **** ApplicationStartingEvent
	 * 
	 * **** ApplicationEnvironmentPreparedEvent
	 * 
	 * **** ApplicationPreparedEvent
	 * 
	 * **** ApplicationStartedEvent
	 * 
	 * **** ApplicationReadyEvent
	 * 
	 * **** ApplicationFailedEvent
	 * 
	 * ********** spring提供ApplicationEvent Publisher *****************
	 * 
	 * ** EventPublishingRunListener
	 * 
	 * **** SimpleApplicationEventMulticaster
	 */
	
}

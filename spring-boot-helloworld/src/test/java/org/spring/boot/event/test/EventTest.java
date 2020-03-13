package org.spring.boot.event.test;

import org.junit.Test;
import org.spring.boot.event.spring.application.ContentEvent;
import org.spring.boot.test.base.BaseTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public class EventTest extends BaseTest {

	@Autowired
	private ApplicationContext applicationContext;
	
	@Test
	public void test() {
		applicationContext.publishEvent(new ContentEvent("您有一个礼包，请查收！"));
	}
}

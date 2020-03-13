package org.spring.boot.controller.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.boot.test.base.BaseWebTest;
import org.springframework.http.ResponseEntity;

public class ControllerTests extends BaseWebTest {

	private static final Logger log = LoggerFactory.getLogger(ControllerTests.class);
	
	@Test
	public void testHomePage() {
		ResponseEntity<String> response = this.restTemplate.getForEntity("/homePage", String.class);
		log.info(response.getBody());
	}

}

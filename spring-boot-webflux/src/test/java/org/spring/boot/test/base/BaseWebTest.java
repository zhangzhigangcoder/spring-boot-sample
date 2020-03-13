package org.spring.boot.test.base;

import java.net.URL;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.spring.boot.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseWebTest {

	@LocalServerPort
	protected int port;
	
	protected URL base;
	
	@Autowired
	protected TestRestTemplate restTemplate;
	
	@Before
	public void setUp() throws Exception {
		String url = String.format("http://localhost:%d", port);
		System.out.println(url);
		this.base = new URL(url);
	}
	
}

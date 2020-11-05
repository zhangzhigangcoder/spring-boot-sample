package org.spring.boot.kafka;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.spring.boot.Application;
import org.spring.boot.kafka.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KafkaTest {

	@Autowired
	private KafkaProducer kafkaProducer;

	@Test
	public void testProduce() {
		String msg = "{\"name\":\"测试消息\"}";
		kafkaProducer.send(msg);
	}
}

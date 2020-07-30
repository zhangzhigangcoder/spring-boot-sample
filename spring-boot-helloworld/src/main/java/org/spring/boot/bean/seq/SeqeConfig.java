package org.spring.boot.bean.seq;

import org.springframework.context.annotation.Bean;

//@Configuration
public class SeqeConfig {

	public SeqeConfig() {
		System.out.println("-----SeqeConfig-------");
	}
	
	@Bean
	public SQInterface sqInterface() {
		return new SQService();
	}
}

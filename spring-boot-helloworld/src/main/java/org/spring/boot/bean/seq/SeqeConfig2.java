package org.spring.boot.bean.seq;

import org.springframework.context.annotation.Bean;

//@Configuration
public class SeqeConfig2 {

	public SeqeConfig2() {
		System.out.println("-----SeqeConfig2-------");
	}
	
	@Bean
	public SQInterface sqInterface() {
		return new SQService2();
	}
}

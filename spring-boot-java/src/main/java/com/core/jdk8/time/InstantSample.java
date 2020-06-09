package com.core.jdk8.time;

import java.time.Duration;
import java.time.Instant;

/**
 * 
 * @author zhangzhigang
 *
 */
public class InstantSample {

	public static void main(String[] args) {
		// current timestamp
		Instant timestamp = Instant.now();
		System.out.println("current timestamp= " + timestamp);
		
		// instant from timestamp
		Instant specificTime = Instant.ofEpochMilli(timestamp.toEpochMilli());
		System.out.println("specific time= " + specificTime);
		
		// duration
		Duration thirtyDay = Duration.ofDays(30);
		System.out.println(thirtyDay);
	}

}

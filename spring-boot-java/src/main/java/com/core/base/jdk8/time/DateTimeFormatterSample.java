package com.core.base.jdk8.time;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeFormatterSample {

	public static void main(String[] args) {
		LocalDate date = LocalDate.now();
		// default format
		System.out.println("default format of LocalDate= " + date);
		// specific format
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MMM-dd");
		System.out.println(date.format(dtf));
		System.out.println(date.format(DateTimeFormatter.BASIC_ISO_DATE));
		
		LocalDateTime dateTime = LocalDateTime.now();
		// default format
		System.out.println("defatul format of LocalDateFormat= " + dateTime);
		// specific format
		DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		System.out.println(dateTime.format(dtf2));
		System.out.println(dateTime.format(DateTimeFormatter.BASIC_ISO_DATE));
		
		Instant timestamp = Instant.now();
		// default format
		System.out.println("default format of Instant= " + timestamp);
		
		// parse example default format: yyyy-MM-dd
		LocalDate date2 = LocalDate.parse("2019-01-22");
//		LocalDate date2 = LocalDate.parse("2019-01-22", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		System.out.println(date2);
		
	}

}

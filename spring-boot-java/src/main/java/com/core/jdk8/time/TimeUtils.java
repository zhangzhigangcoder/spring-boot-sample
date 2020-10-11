package com.core.jdk8.time;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class TimeUtils {

	public static void main(String[] args) {
		Date date = new Date();
		System.out.println(date);
		LocalDate localDate = dateToLocalDate(date);
		System.out.println(localDate);
	}
	
	
	/**
	 * Date to LocalDate
	 * @param date
	 * @return
	 */
	public static LocalDate dateToLocalDate(Date date) {
		return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).toLocalDate();
	}
	
	/**
	 * LocalDate to Date
	 * @param localDate
	 * @return
	 */
	public static Date localDateToDate(LocalDate localDate) {
		return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}
	
	/**
	 * Date to LocalTime
	 * @param date
	 * @return
	 */
	public static LocalTime dateToLocalTime(Date date) {
		return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).toLocalTime();
	}
	
	/**
	 * LocalTime to Date
	 * @param localTime
	 * @return
	 */
	public static Date localTimeToDate(LocalTime localTime) {
		return Date.from(LocalDateTime.of(LocalDate.now(), localTime).atZone(ZoneId.systemDefault()).toInstant());
	}
	
	/**
	 * Date to LocalDateTime
	 * @param date
	 * @return
	 */
	public static LocalDateTime dateToLocalDateTime(Date date) {
		return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
	}
	
	/**
	 * LocalDateTime to Date
	 * @param localDateTime
	 * @return
	 */
	public static Date localDateTimeToDate(LocalDateTime localDateTime) {
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}
	
	//Calendar to Instant
	public static Instant calendarToInstant(Calendar calendar) {
		return calendar.toInstant();
		
	}
	
}

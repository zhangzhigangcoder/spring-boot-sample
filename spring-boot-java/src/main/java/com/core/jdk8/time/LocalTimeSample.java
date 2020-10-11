package com.core.jdk8.time;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

/**
 * Immutability Thread safe
 * 
 * @author zhangzhigang
 *
 */
public class LocalTimeSample {

	public static void main(String[] args) {

		betweenSeconds();
	}

	/**
	 * build LocalTime
	 */
	public static void buildTime() {
		// current time hh:mm:ss.zzz
		LocalTime today = LocalTime.now();
		System.out.println("current time= " + today);

		// creating time by zone "Asia/Kolkata"
		LocalTime specificZone = LocalTime.now(ZoneId.of("Asia/Kolkata"));
		System.out.println("current time in IST= " + specificZone);

		// LocalTime.now(clock);

		// creating time by input arguments
		LocalTime specificTime = LocalTime.of(12, 23, 59, 100);
		System.out.println(specificTime);

		// creating time from the base i.e 01/01/1970
		LocalTime timeFromBase = LocalTime.ofNanoOfDay(3600 * 24);
		timeFromBase = LocalTime.ofSecondOfDay(3600 * 23);
		System.out.println("365th time from base date= " + timeFromBase);
	}

	/**
	 * set time field
	 */
	public static void setTime() {
		LocalTime now = LocalTime.now();
		System.out.println(now);
		// set hour
		System.out.println(now.withHour(23));
		// set minute
		System.out.println(now.withMinute(59));
		// set second
		System.out.println(now.withSecond(59));
		// set second
		System.out.println(now.with(ChronoField.SECOND_OF_MINUTE, 58));
		// set nano
		System.out.println(now.withNano(1000));
	}

	/**
	 * get time field
	 */
	public static void getTime() {
		LocalTime now = LocalTime.now();
		// hour 1-23
		System.out.println(now.getHour());
		// minute
		System.out.println(now.getMinute());
		// second
		System.out.println(now.getSecond());
		// nano
		System.out.println(now.getNano());
		// hour
		System.out.println(now.getLong(ChronoField.HOUR_OF_DAY));
		// 
		System.out.println(now.toSecondOfDay());
		System.out.println(now.toNanoOfDay());
	}

	/**
	 * compare time
	 */
	public static void compareTime() {
		LocalTime d1 = LocalTime.of(23, 1, 22);
		LocalTime d2 = LocalTime.now();
		// after
		System.out.println(d1.isAfter(d2));
		// before
		System.out.println(d1.isBefore(d2));
		// hours
		System.out.println(d2.until(d1, ChronoUnit.HOURS));
	}

	/**
	 * compute LocalTime
	 */
	public static void computeTime() {
		LocalTime now = LocalTime.now();

		System.out.println(now);
		// minus hours
		System.out.println(now.minusHours(2));
		// minus minutes 
		System.out.println(now.minusMinutes(2));
		// minus seconds
		System.out.println(now.minusSeconds(1));
		// minus nanos
		System.out.println(now.minusNanos(1));

		// plus hours
		System.out.println(now.plusHours(2));
		// plus minutes
		System.out.println(now.plusMinutes(2));
		// plus seconds
		System.out.println(now.plusSeconds(1));
		// plus nanos
		System.out.println(now.plusNanos(1));

	}

	/**
	 * LocalTime convert to LocalDateTime
	 */
	public static void convertToLocalDateTime() {
		LocalTime now = LocalTime.now();
		System.out.println(now.atDate(LocalDate.now()));
	}

	/**
	 * 计算两个时间之间间隔秒数
	 * 
	 * @return
	 */
	public static void betweenSeconds() {
		LocalTime d1 = LocalTime.of(13, 1, 22);
		LocalTime d2 = LocalTime.now();
		System.out.println(d1.until(d2, ChronoUnit.SECONDS));
		// or
		System.out.println(d2.toSecondOfDay() - d1.toSecondOfDay());

	}
}

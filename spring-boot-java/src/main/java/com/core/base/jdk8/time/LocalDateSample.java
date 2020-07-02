package com.core.base.jdk8.time;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

/**
 * Immutability Thread safe
 * 
 * @author zhangzhigang
 *
 */
public class LocalDateSample {

	public static void main(String[] args) {

		setDate();
		System.out.println("--------------");
		getDate();
	}

	/**
	 * build LocalDate
	 */
	public static void buildDate() {
		// current date yyyy-MM-dd
		LocalDate today = LocalDate.now();
		System.out.println("current date= " + today);

		// creating LocalDate by zone "Asia/Kolkata"
		LocalDate todayKolkata = LocalDate.now(ZoneId.of("Asia/Kolkata"));
		System.out.println("current date in IST= " + todayKolkata);

		// LocalDate.now(clock);

		// creating LocalDate by input arguments
		LocalDate firstDayOf2019 = LocalDate.of(2019, Month.JANUARY, 1);
		firstDayOf2019 = LocalDate.of(2019, 1, 1);
		System.out.println(firstDayOf2019);

		// creating date from the base i.e 01/01/1970
		LocalDate dateFromBase = LocalDate.ofEpochDay(365);
		System.out.println("365th day from base date= " + dateFromBase);

		// creating date from the specified year
		LocalDate hundredDay2018 = LocalDate.ofYearDay(2018, 100);
		System.out.println("100th day of 2018= " + hundredDay2018);

	}

	public static void setDate() {
		LocalDate now = LocalDate.now();
		System.out.println(now);
		// set year
		System.out.println(now.withYear(2018));
		// set month
		System.out.println(now.withMonth(2));
		// set dayOfWeek
		System.out.println(now.with(ChronoField.DAY_OF_WEEK, 5));
		// set dayOfMonth
		System.out.println(now.withDayOfMonth(23));
		// set dayOfMonth
		System.out.println(now.withDayOfYear(23));
		// 设置当月第一天
		System.out.println(now.with(TemporalAdjusters.firstDayOfMonth()));
	}

	/**
	 * get date field
	 */
	public static void getDate() {
		LocalDate now = LocalDate.now();
		// year
		System.out.println(now.getYear());
		// month 1-12
		System.out.println(now.getMonth().getValue());
		// day of month 1-31
		System.out.println(now.getDayOfMonth());
		// day of week 1-7
		System.out.println(now.getDayOfWeek());
		// day of year
		System.out.println(now.getDayOfYear());
		// day of year
		System.out.println(now.get(ChronoField.DAY_OF_YEAR));
		// days of month
		System.out.println(now.lengthOfMonth());
		// days of year
		System.out.println(now.lengthOfYear());
		// days from  1970-01-01 
		System.out.println(now.toEpochDay());
		// last day of month
		System.out.println(now.range(ChronoField.DAY_OF_MONTH).getMaximum());
	}

	/**
	 * compare date
	 */
	public static void compareDate() {
		LocalDate d1 = LocalDate.of(2012, 1, 22);
		LocalDate d2 = LocalDate.now();
		// after
		System.out.println(d1.isAfter(d2));
		// before
		System.out.println(d1.isBefore(d2));
		// equal
		System.out.println(d1.isEqual(d2));
		// leapYear
		System.out.println(d1.isLeapYear());
		// <0 =0 >0
		System.out.println(d1.compareTo(d2));
		// years between d1 and d2
		System.out.println(d1.until(d2).getYears());
		// months between d1 and d2 (当年)
		System.out.println(d1.until(d2).getMonths());
		// days between d1 and d2 (当月)
		System.out.println(d1.until(d2).getDays());
		// days between d1 and d2 (跨年 跨月)
		System.out.println(d1.until(d2, ChronoUnit.DAYS));
	}

	/**
	 * compute LocalDate
	 */
	public static void computeDate() {
		LocalDate now = LocalDate.now();

		System.out.println(now);
		// minus days
		System.out.println(now.minusDays(2));
		// minus months
		System.out.println(now.minusMonths(2));
		// minus weeks
		System.out.println(now.minusWeeks(1));
		// minus years
		System.out.println(now.minusYears(1));

		// plus days
		System.out.println(now.plusDays(2));
		// plus months
		System.out.println(now.plusMonths(2));
		// plus weeks
		System.out.println(now.plusWeeks(1));
		// plus years
		System.out.println(now.plusYears(1));
		// plus years
		System.out.println(now.plus(1, ChronoUnit.YEARS));

	}

	/**
	 * LocalDate convert to LocalDateTime
	 */
	public static void convertToLocalDateTime() {
		LocalDate now = LocalDate.now();
		// set hour-00 minute-00
		System.out.println(now.atStartOfDay());
		LocalTime nowTime = LocalTime.now();
		System.out.println(nowTime);
		System.out.println(now.atTime(nowTime));
		// set hour(0-23) minute(0-59)
		System.out.println(now.atTime(11, 23));
		// set hour minute seconds
		System.out.println(now.atTime(11, 23, 55));
		// set hour minute seconds nanoOfSecond
		System.out.println(now.atTime(11, 23, 55, 100));
	}

	/**
	 * 计算两个日期之间间隔天数
	 * 
	 * @param date
	 * @param anotherDate
	 * @return
	 */
	public static void betweenDays() {
		LocalDate d1 = LocalDate.of(2018, 1, 22);
		LocalDate d2 = LocalDate.now().plusDays(1);
		System.out.println(d1.until(d2, ChronoUnit.DAYS));
		// or
		System.out.println(d2.toEpochDay() - d1.toEpochDay());

	}
}

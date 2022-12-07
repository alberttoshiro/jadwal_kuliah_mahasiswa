package com.albert.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public class AppUtil {

  private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
  private static final DateTimeFormatter DATE_TIME_FORMATTER =
      DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
  private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

  public static String convertLocalDateTimeToString(LocalDateTime localDateTime) {
    return DATE_TIME_FORMATTER.format(localDateTime);
  }

  public static String convertLocalDateTimeToStringDate(LocalDateTime localDateTime) {
    return DATE_FORMATTER.format(localDateTime);
  }

  public static String convertLocalDateTimeToTimeString(LocalDateTime localDateTime) {
    return TIME_FORMATTER.format(localDateTime);
  }

  public static String convertLocalTimeToString(LocalTime localTime) {
    return TIME_FORMATTER.format(localTime);
  }

  public static LocalDateTime convertStringToLocalDateTime(String date) {
    return LocalDate.parse(date, DATE_FORMATTER).atStartOfDay();
  }

  public static LocalTime convertStringToLocalTime(String stringTime) {
    return LocalTime.parse(stringTime, TIME_FORMATTER);
  }

  public static DayOfWeek getDayEnum(String dayOfWeek) {
    if (dayOfWeek.equals("Senin")) {
      return DayOfWeek.MONDAY;
    } else if (dayOfWeek.equals("Selasa")) {
      return DayOfWeek.TUESDAY;
    } else if (dayOfWeek.equals("Rabu")) {
      return DayOfWeek.WEDNESDAY;
    } else if (dayOfWeek.equals("Kamis")) {
      return DayOfWeek.THURSDAY;
    } else if (dayOfWeek.equals("Jumat")) {
      return DayOfWeek.FRIDAY;
    } else if (dayOfWeek.equals("Sabtu")) {
      return DayOfWeek.SATURDAY;
    } else if (dayOfWeek.equals("Minggu")) {
      return DayOfWeek.SUNDAY;
    }
    return null;
  }

  public static LocalDateTime getNextDayOfWeekDate(LocalDateTime startPeriod, DayOfWeek dayOfWeek) {
    LocalDateTime startPeriodNext = startPeriod.with(TemporalAdjusters.nextOrSame(dayOfWeek));
    return startPeriodNext;
  }

  public static LocalDateTime getNextLocalDateTime(String startPeriod, String dayName,
      String time) {
    LocalDateTime startPeriodDate = convertStringToLocalDateTime(startPeriod);
    DayOfWeek dayOfWeek = getDayEnum(dayName);
    LocalDateTime localDateTime = getNextDayOfWeekDate(startPeriodDate, dayOfWeek);
    LocalTime localTime = convertStringToLocalTime(time);
    return localDateTime.with(localTime);
  }

  public static List<LocalDateTime> getRecurringDate(String startPeriod, String endPeriod,
      String dayName, String time) {
    List<LocalDateTime> listRecurringDate = new ArrayList<>();
    LocalDateTime startDayDate = getNextLocalDateTime(startPeriod, dayName, time);
    LocalDateTime endPeriodDate = convertStringToLocalDateTime(endPeriod).plusDays(1);
    while (startDayDate.compareTo(endPeriodDate) < 0) {
      listRecurringDate.add(startDayDate);
      startDayDate = startDayDate.plusWeeks(1);
    }
    return listRecurringDate;
  }

  public static boolean isAnyOverlap(LocalDateTime startDate, LocalDateTime endDate,
      List<LocalDateTime> startSearchDateList, List<LocalDateTime> endSearchDateList) {
    for (int i = 0; i < startSearchDateList.size(); i++) {
      if (isOverlap(startDate, endDate, startSearchDateList.get(i), endSearchDateList.get(i))) {
        return true;
      }
    }
    return false;
  }

  public static boolean isOverlap(LocalDateTime startDate, LocalDateTime endDate,
      LocalDateTime startSearchDate, LocalDateTime endSearchDate) {
    if (endDate.compareTo(startSearchDate) <= 0 || startDate.compareTo(endSearchDate) >= 0) {
      return false;
    }
    return true;
  }

  public static void main(String[] args) {
    String namaHari = "Rabu";
    String waktu = "15:20";
    String periodeMulai = "2022-09-03";
    LocalDateTime dateTime = getNextLocalDateTime(periodeMulai, namaHari, waktu);
    String dateTimeString = convertLocalDateTimeToString(dateTime);
    System.out.println(dateTime);
    System.out.println(dateTimeString);
    System.out.println(convertLocalDateTimeToTimeString(dateTime));
  }
}

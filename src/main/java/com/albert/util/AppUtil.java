package com.albert.util;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class AppUtil {

  private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

  public static String convertLocalTimeToString(LocalTime localTime) {
    return TIME_FORMATTER.format(localTime);
  }

  public static LocalTime convertStringToLocalTime(String stringTime) {
    return LocalTime.parse(stringTime, TIME_FORMATTER);
  }
}

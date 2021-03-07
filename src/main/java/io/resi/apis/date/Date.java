package io.resi.apis.date;

import lombok.Data;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
public class Date {
  private static final String[] ACCEPTED_DATE_FORMATS = { "MM/dd/yy", "MMMM d, yyyy", "MMM-d-yyyy" };

  private final int day;
  private final int month;
  private final int year;

  public Date(final int day, final int month, final int year) {
    this.day = day;
    this.month = month;
    this.year = year;
  }

  public static Date of(final String date) {
    for (final String dateFormat : ACCEPTED_DATE_FORMATS) {
      final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
      try {
        final LocalDate parsedDate = LocalDate.parse(date, formatter);
        return new Date(parsedDate.getDayOfMonth(), parsedDate.getMonthValue(), parsedDate.getYear());
      } catch (final DateTimeException ignored) {

      }
    }
    return null;
  }
}

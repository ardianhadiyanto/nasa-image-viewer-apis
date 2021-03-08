package io.resi.apis.date.provider;

import io.resi.apis.date.Date;

import java.util.List;

public interface DateProvider {
  List<Date> getAvailableDates();
}

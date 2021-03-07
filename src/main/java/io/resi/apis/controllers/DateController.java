package io.resi.apis.controllers;

import io.resi.apis.date.Date;
import io.resi.apis.date.DateProvider;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
class DateController {
  private final DateProvider dateProvider;

  DateController(final DateProvider dateProvider) {
    this.dateProvider = dateProvider;
  }
  @GetMapping(value = "/dates", produces = MediaType.APPLICATION_JSON_VALUE)
  List<Date> getDates() {
    return dateProvider.getDates();
  }
}

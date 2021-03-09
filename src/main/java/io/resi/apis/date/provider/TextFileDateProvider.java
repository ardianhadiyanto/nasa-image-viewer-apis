package io.resi.apis.date.provider;

import io.resi.apis.date.Date;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Component
class TextFileDateProvider implements DateProvider {
  private final List<Date> dates;

  public TextFileDateProvider(@Value("${date.source.file}") final String textFilePath) throws IOException {
    this.dates = getDatesFrom(textFilePath);
  }

  private List<Date> getDatesFrom(String pathToFile) throws IOException {
    final List<Date> parsedDates = new ArrayList<>();
    try (InputStream inputStream = getClass().getResourceAsStream(pathToFile)) {
      IOUtils.readLines(inputStream, StandardCharsets.UTF_8)
        .forEach(line -> {
          final Date date = Date.of(line);
          parsedDates.add(date);
        });
    }

    return parsedDates;
  }

  @Override
  public List<Date> getAvailableDates() {
    return dates;
  }
}

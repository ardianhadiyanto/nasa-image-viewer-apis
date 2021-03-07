package io.resi.apis.date.provider;

import io.resi.apis.date.Date;
import io.resi.apis.date.DateProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Component
class TextFileDateProvider implements DateProvider {
  private final List<Date> dates;

  public TextFileDateProvider(@Value( "${source.file}" )final String textFilePath) throws IOException {
    this.dates = getDatesFrom(textFilePath);
  }

  private List<Date> getDatesFrom(String pathToFile) throws IOException {
    final List<Date> parsedDates = new ArrayList<>();
    try (final Stream<String> lines = Files.lines(Paths.get(pathToFile))) {
      lines.forEach(line -> {
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

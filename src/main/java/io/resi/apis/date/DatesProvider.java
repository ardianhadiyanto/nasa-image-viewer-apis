package io.resi.apis.date;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Component
class DatesProvider {
  private final List<Date> dates;

  public DatesProvider(final String textFilePath) throws IOException {
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

  public List<Date> getDates() {
    return dates;
  }
}

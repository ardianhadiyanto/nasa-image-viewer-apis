package io.resi.apis.date.provider;

import io.resi.apis.date.Date;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class TextFileDateProviderTests {
  private final static String FILE_PATH = "/image-dates.txt";

  @Test
  void constructor_givenValidPathToFile_noExceptionIsThrown() {
    assertThatCode(() -> new TextFileDateProvider(FILE_PATH))
      .doesNotThrowAnyException();
  }

  @Test
  void getDates_whenDatesProviderIsCreatedWithValidPathToFile_returnAllDatesInTheFile() throws IOException {
    final TextFileDateProvider sut = new TextFileDateProvider(FILE_PATH);

    final List<Date> actual = sut.getAvailableDates();

    assertThat(actual).isNotEmpty();
  }
}

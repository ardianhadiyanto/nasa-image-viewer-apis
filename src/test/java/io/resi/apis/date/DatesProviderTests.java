package io.resi.apis.date;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class DatesProviderTests {
  private final static String FILE_PATH = "src/test/resources/image-dates.txt";
  @Test
  void constructor_givenInvalidPathToFile_throwException() {
    assertThatIOException()
      .isThrownBy(() -> { new DatesProvider("invalid"); });
  }

  @Test
  void constructor_givenValidPathToFile_noExceptionIsThrown() {
    assertThatCode(() -> new DatesProvider(FILE_PATH))
      .doesNotThrowAnyException();
  }

  @Test
  void getDates_whenDatesProviderIsCreatedWithValidPathToFile_returnAllDatesInTheFile() throws IOException {
    final DatesProvider sut = new DatesProvider(FILE_PATH);

    final List<Date> actual = sut.getDates();

    assertThat(actual).isNotEmpty();
  }
}

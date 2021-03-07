package io.resi.apis.date;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class DateTests {
  @ParameterizedTest
  @ValueSource(strings = {"02/27/17", "February 27, 2017", "Feb-27-2017"})
  void of_givenValidDateString_returnExpected(final String date) {
    final Date expected = new Date(27, 2, 2017);

    final Date actual = Date.of(date);

    assertThat(actual).isEqualTo(expected);
  }

  @ParameterizedTest
  @ValueSource(strings = {"invalid", "27.02.2017"})
  void of_givenUnsupportedDateString_returnNull(final String date) {
    final Date actual = Date.of(date);

    assertThat(actual).isNull();
  }
}

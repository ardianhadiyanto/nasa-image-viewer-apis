package io.resi.apis.nasa;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
class RoverDto {
  private String name;

  void setName(final String name) {
    this.name = name;
  }

  String getName() {
    return name;
  }
}

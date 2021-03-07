package io.resi.apis.nasa;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
class RoverDto {
  void setName(String name) {
    this.name = name;
  }

  private String name;

  String getName() {
    return name;
  }
}

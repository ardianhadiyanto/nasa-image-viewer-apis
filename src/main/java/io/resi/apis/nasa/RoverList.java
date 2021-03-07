package io.resi.apis.nasa;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class RoverList {
  private List<RoverDto> rovers = new ArrayList<>();

  void setRovers(List<RoverDto> rovers) {
    this.rovers = rovers;
  }

  List<String> getRovers() {
    return rovers.stream()
      .map(RoverDto::getName)
      .map(String::toLowerCase)
      .collect(Collectors.toList());
  }
}

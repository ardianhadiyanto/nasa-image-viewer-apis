package io.resi.apis.rover;

import io.resi.apis.date.Date;

import java.util.List;

public interface RoverProvider {
  List<String> getRovers();
  List<RoverImage> getRoverImages(final String roverName, final Date date);
}

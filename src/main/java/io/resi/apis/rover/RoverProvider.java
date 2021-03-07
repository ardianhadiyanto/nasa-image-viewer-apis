package io.resi.apis.rover;

import java.util.List;

public interface RoverProvider {
  List<String> getRovers();
  List<RoverImage> getRoverImages(final String roverName, final String date);
}

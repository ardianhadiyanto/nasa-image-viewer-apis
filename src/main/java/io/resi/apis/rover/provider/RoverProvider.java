package io.resi.apis.rover.provider;

import io.resi.apis.date.Date;
import io.resi.apis.rover.RoverImage;

import java.util.List;

public interface RoverProvider {
  List<String> getRovers();
  List<RoverImage> getRoverImages(final String roverName, final Date date);
  byte[] getRoverImage(final String url);
}

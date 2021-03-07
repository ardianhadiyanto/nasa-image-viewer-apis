package io.resi.apis.rover;

import lombok.Data;

@Data
public class RoverImage {
  private final String rover;
  private final String camera;
  private final String dateTaken;
  private final String imageUrl;

  public RoverImage(final String rover, final String camera, final String dateTaken, final String imageUrl) {
    this.rover = rover;
    this.camera = camera;
    this.dateTaken = dateTaken;
    this.imageUrl = imageUrl;
  }
}

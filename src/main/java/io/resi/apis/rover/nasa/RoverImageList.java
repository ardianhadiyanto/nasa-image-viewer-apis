package io.resi.apis.rover.nasa;

import io.resi.apis.rover.RoverImage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class RoverImageList {
  private List<RoverImageDto> photos = new ArrayList<>();

  public List<RoverImageDto> getPhotos() {
    return photos;
  }

  void setPhotos(final List<RoverImageDto> photos) {
    this.photos = photos;
  }

  List<RoverImage> getRoverImages() {
    return photos.stream()
      .map(RoverImageDto::toRoverImage)
      .collect(Collectors.toList());
  }
}

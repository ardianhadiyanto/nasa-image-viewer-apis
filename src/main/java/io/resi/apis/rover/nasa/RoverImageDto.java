package io.resi.apis.rover.nasa;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.resi.apis.rover.RoverImage;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
class RoverImageDto {
  private String roverName;
  private String cameraName;

  @JsonProperty("earth_date")
  private String earthDate;

  @JsonProperty("img_src")
  private String imageUrl;

  void setRoverName(final String roverName) {
    this.roverName = roverName;
  }

  public String getRoverName() {
    return roverName;
  }

  public String getCameraName() {
    return cameraName;
  }

  public void setCameraName(final String cameraName) {
    this.cameraName = cameraName;
  }

  @JsonProperty("rover")
  private void unpackRover(Map<String, Object> rover) {
    this.roverName = rover.get("name").toString();
  }

  @JsonProperty("camera")
  private void unpackCamera(Map<String, Object> camera) {
    this.cameraName = camera.get("name").toString();
  }

  public String getEarthDate() {
    return earthDate;
  }

  public void setEarthDate(final String earthDate) {
    this.earthDate = earthDate;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(final String imageUrl) {
    this.imageUrl = imageUrl;
  }

  RoverImage toRoverImage() {
    return new RoverImage(roverName, cameraName, earthDate, imageUrl);
  }
}

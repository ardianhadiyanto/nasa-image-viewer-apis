package io.resi.apis.rover.nasa;

import io.resi.apis.date.Date;
import io.resi.apis.rover.RoverImage;
import io.resi.apis.rover.RoverProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
class NasaRestApiClient implements RoverProvider {
  private static final String BASE_URL = "https://api.nasa.gov/mars-photos/api/v1";
  private static final String DATE_PATTERN = "yyyy-MM-dd";
  private final RestTemplate restTemplate;
  private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
  private final String apiKey;

  public NasaRestApiClient(@Value("${nasa.api.key}") final String apiKey, final RestTemplate restTemplate) {
    this.apiKey = apiKey;
    this.restTemplate = restTemplate;
  }

  @Override
  public List<RoverImage> getRoverImages(final String roverName, final Date date) {
    final String url = new StringBuilder(BASE_URL)
      .append("/rovers")
      .append("/").append(roverName)
      .append("/photos")
      .append("?earth_date=").append(formatDate(date))
      .append("&api_key=").append(apiKey)
      .toString();

    final ResponseEntity<RoverImageList> roverImageList = restTemplate.getForEntity(url, RoverImageList.class);
    return roverImageList.getBody().getRoverImages();
  }

  @Override
  public byte[] getRoverImage(final String imageUrl) {
    final byte[] imageBytes = restTemplate.getForObject(imageUrl, byte[].class);
    return imageBytes;
  }

  private String formatDate(final Date date) {
    return LocalDate.of(date.getYear(), date.getMonth(), date.getDay())
      .format(formatter);
  }

  @Override
  public List<String> getRovers() {
    final String url = new StringBuilder(BASE_URL)
      .append("/rovers")
      .append("?api_key=").append(apiKey)
      .toString();

    final ResponseEntity<RoverList> roverList = restTemplate.getForEntity(url, RoverList.class);
    return roverList.getBody().getRovers();
  }
}
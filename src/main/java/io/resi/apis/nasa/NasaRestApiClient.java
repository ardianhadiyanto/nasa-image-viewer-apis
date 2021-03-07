package io.resi.apis.nasa;

import io.resi.apis.rover.RoverImage;
import io.resi.apis.rover.RoverProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
class NasaRestApiClient implements RoverProvider {
  private static final String BASE_URL = "https://api.nasa.gov/mars-photos/api/v1";
  private final RestTemplate restTemplate;
  private final String apiKey;

  public NasaRestApiClient(@Value("${nasa.api.key}") final String apiKey, final RestTemplate restTemplate) {
    this.apiKey = apiKey;
    this.restTemplate = restTemplate;
  }

  @Override
  public List<RoverImage> getRoverImages(final String roverName, final String date) {
    final String url = new StringBuilder(BASE_URL)
      .append("/rovers")
      .append("/").append(roverName)
      .append("/photos")
      .toString();

    return Arrays.asList();
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

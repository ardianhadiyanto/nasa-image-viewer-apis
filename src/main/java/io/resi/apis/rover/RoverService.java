package io.resi.apis.rover;

import io.resi.apis.date.Date;
import io.resi.apis.date.DateProvider;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class RoverService {
  private final RoverProvider roverProvider;
  private final DateProvider dateProvider;

  public RoverService(final RoverProvider roverProvider, final DateProvider dateProvider) {
    this.roverProvider = roverProvider;
    this.dateProvider = dateProvider;
  }

  public List<RoverImage> getAvailableRoverImages() {
    final List<String> rovers = roverProvider.getRovers();
    final List<Date> supportedDates = dateProvider.getAvailableDates();

    final List<RoverImage> roverImages = new LinkedList<>();
    rovers.forEach(rover ->
      supportedDates.forEach(supportedDate ->
        roverImages.addAll(roverProvider.getRoverImages(rover, supportedDate))
      )
    );

    return roverImages;
  }

  public byte[] getRoverImage(final String imageUrl) {
    return roverProvider.getRoverImage(imageUrl);
  }
}

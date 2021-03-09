package io.resi.apis.rover;

import io.resi.apis.date.Date;
import io.resi.apis.date.provider.DateProvider;
import io.resi.apis.rover.provider.RoverProvider;
import io.resi.apis.storage.StorageService;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class RoverService {
  private final RoverProvider roverProvider;
  private final DateProvider dateProvider;
  private final StorageService storageService;
  private final Logger logger = LoggerFactory.getLogger(RoverService.class);


  public RoverService(final RoverProvider roverProvider, final DateProvider dateProvider, final StorageService storageService) {
    this.roverProvider = roverProvider;
    this.dateProvider = dateProvider;
    this.storageService = storageService;
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
    final String fileNameHash = DigestUtils.sha256Hex(imageUrl);

    byte[] imageBytes;
    if (storageService.exists(fileNameHash)) {
      logger.info("Fetching image from local storage");
      imageBytes = storageService.retrieve(fileNameHash);
    } else {
      logger.info("Downloading image...");
      imageBytes = roverProvider.getRoverImage(imageUrl);
      storageService.store(fileNameHash, imageBytes);
      logger.info("Storing image...");
    }

    return imageBytes;
  }
}

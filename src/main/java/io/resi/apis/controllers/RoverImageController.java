package io.resi.apis.controllers;

import io.resi.apis.rover.RoverImage;
import io.resi.apis.rover.RoverService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/images")
class RoverImageController {
  private final RoverService roverService;

  RoverImageController(final RoverService roverService) {
    this.roverService = roverService;
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  List<RoverImage> getAvailableRoverImages() {
    return roverService.getAvailableRoverImages();
  }

  @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = MediaType.IMAGE_JPEG_VALUE)
  byte[] getAvailableRoverImages(@RequestBody final ImageRequest request) {
    return roverService.getRoverImage(request.getUrl());
  }
}

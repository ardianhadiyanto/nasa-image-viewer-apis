package io.resi.apis.controllers;

import io.resi.apis.rover.RoverImage;
import io.resi.apis.rover.RoverService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1")
class RoverController {
  private final RoverService roverService;

  RoverController(final RoverService roverService) {
    this.roverService = roverService;
  }

  @GetMapping(value = "/images", produces = MediaType.APPLICATION_JSON_VALUE)
  List<RoverImage> getAvailableRoverImages() {
    return roverService.getAvailableRoverImages();
  }
}

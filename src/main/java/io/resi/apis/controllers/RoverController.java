package io.resi.apis.controllers;

import io.resi.apis.rover.RoverImage;
import io.resi.apis.rover.RoverProvider;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
class RoverController {
  private final RoverProvider roverProvider;

  RoverController(final RoverProvider roverProvider) {
    this.roverProvider = roverProvider;
  }

  @GetMapping(value = "/rovers", produces = MediaType.APPLICATION_JSON_VALUE)
  List<String> getRovers() {
    return roverProvider.getRovers();
  }

  @GetMapping(value = "/rovers/{name}/images", produces = MediaType.APPLICATION_JSON_VALUE)
  List<RoverImage> getRoverImages(@PathVariable final String name, @RequestParam("date") final String date) {
    return roverProvider.getRoverImages(name, date);
  }
}

package io.resi.apis.nasa;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
class NasaRestConfig {
  @Bean
  RestTemplate restTemplate() {
    return new RestTemplate();
  }
}

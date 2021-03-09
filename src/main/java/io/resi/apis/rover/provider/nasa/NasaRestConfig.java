package io.resi.apis.rover.provider.nasa;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
class NasaRestConfig {
  @Bean
  RestTemplate restTemplate() {
    final HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
    final HttpClient httpClient = HttpClientBuilder.create().setRedirectStrategy(new LaxRedirectStrategy()).build();
    factory.setHttpClient(httpClient);

    final RestTemplate restTemplate = new RestTemplate();
    restTemplate.setRequestFactory(factory);

    return restTemplate;
  }
}

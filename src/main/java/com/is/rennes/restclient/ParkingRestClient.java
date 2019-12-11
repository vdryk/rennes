package com.is.rennes.restclient;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ParkingRestClient {

    private RestTemplate restTemplate;

    public ParkingRestClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.rootUri("https://data.rennesmetropole.fr").build();
    }

    public ParkingRennes fetchRennes() {
        return restTemplate.getForObject(
                "/api/records/1.0/search/?dataset=export-api-parking-citedia",
                ParkingRennes.class);
    }
}

package com.is.rennes.restclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParkingRestClient {

    private static final int PAGE_SIZE = 5;

    @Value("${rootUri}")
    private String rootUri;

    private RestTemplate restTemplate;

    public ParkingRestClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public List<PageRennes> fetchRennes() {

        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromUriString(rootUri + "/api/records/1.0/search/")
                .queryParam("dataset", "export-api-parking-citedia")
                .queryParam("rows", PAGE_SIZE);

        List<PageRennes> pageRennes = new ArrayList<>();
        PageRennes page;
        int index = 0;
        do {
            page = restTemplate.getForObject(
                    uriBuilder.replaceQueryParam("start", index).toUriString(),
                    PageRennes.class);
            pageRennes.add(page);
            index += PAGE_SIZE;
        } while (pageIsNotEmpty(page));

        return pageRennes;
    }

    private boolean pageIsNotEmpty(PageRennes page) {
        return page != null && page.getRecords() != null && page.getRecords().size() != 0;
    }
}

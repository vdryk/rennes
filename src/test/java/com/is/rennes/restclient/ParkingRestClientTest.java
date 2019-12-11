package com.is.rennes.restclient;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@RestClientTest(ParkingRestClient.class)
class ParkingRestClientTest {

    @Autowired
    private ParkingRestClient parkingRestClient;

    @Autowired
    private MockRestServiceServer mockServer;

    @Test
    void fetchRennes() {

        String rootUri = "https://data.rennesmetropole.fr";

        mockServer
                .expect(requestTo(rootUri + "/api/records/1.0/search/?dataset=export-api-parking-citedia&rows=5&start=0"))
                .andRespond(withSuccess(new ClassPathResource("restresponses/data.rennesmetropole-page1.json"), MediaType.APPLICATION_JSON));

        mockServer
                .expect(requestTo(rootUri + "/api/records/1.0/search/?dataset=export-api-parking-citedia&rows=5&start=5"))
                .andRespond(withSuccess(new ClassPathResource("restresponses/data.rennesmetropole-page2.json"), MediaType.APPLICATION_JSON));

        mockServer
                .expect(requestTo(rootUri + "/api/records/1.0/search/?dataset=export-api-parking-citedia&rows=5&start=10"))
                .andRespond(withSuccess(new ClassPathResource("restresponses/data.rennesmetropole-page3.json"), MediaType.APPLICATION_JSON));

        List<PageRennes> pageRennesList = parkingRestClient.fetchRennes();

        mockServer.verify();

        assertEquals(
                10,
                pageRennesList.stream()
                        .flatMap(pageRennes -> pageRennes.getRecords().stream())
                        .collect(toList())
                        .size());
    }
}
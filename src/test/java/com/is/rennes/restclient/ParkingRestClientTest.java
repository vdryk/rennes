package com.is.rennes.restclient;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

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

        mockServer
                .expect(requestTo("/api/records/1.0/search/?dataset=export-api-parking-citedia"))
                .andRespond(withSuccess(new ClassPathResource("restresponses/data.rennesmetropole.fr.json"), MediaType.APPLICATION_JSON));

        ParkingRennes parkingRennes = parkingRestClient.fetchRennes();

        mockServer.verify();

        assertEquals(10, parkingRennes.getRecords().size());
    }
}
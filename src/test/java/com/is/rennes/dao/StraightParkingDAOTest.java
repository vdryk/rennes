package com.is.rennes.dao;

import com.is.rennes.controller.rest.Parking;
import com.is.rennes.mapper.ParkingMapper;
import com.is.rennes.restclient.PageRennes;
import com.is.rennes.restclient.ParkingRestClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StraightParkingDAOTest {

    private StraightParkingDAO straightParkingDAO;

    @Mock
    private ParkingRestClient parkingRestClient;

    @Mock
    private ParkingMapper parkingMapper;

    @Before
    public void setUp() {
        straightParkingDAO = new StraightParkingDAO(parkingRestClient, parkingMapper);
    }

    @Test
    public void shouldGetParkings() {
        // given
        List<PageRennes> pageRennes = Collections.singletonList(new PageRennes());
        when(parkingRestClient.fetchRennes()).thenReturn(pageRennes);
        List<Parking> expectedParkings = Collections.singletonList(Parking.builder().build());
        when(parkingMapper.toParking(pageRennes)).thenReturn(expectedParkings);

        // when
        List<Parking> actualParkings = straightParkingDAO.getParking("Rennes");

        // then
        assertEquals(expectedParkings, actualParkings);
    }

}
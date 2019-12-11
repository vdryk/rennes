package com.is.rennes.dao;

import com.is.rennes.controller.rest.Parking;
import com.is.rennes.mapper.ParkingMapper;
import com.is.rennes.restclient.ParkingRestClient;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StraightParkingDAO implements ParkingDAO {

    private ParkingRestClient parkingRestClient;
    private ParkingMapper parkingMapper;

    public StraightParkingDAO(ParkingRestClient parkingRestClient, ParkingMapper parkingMapper) {
        this.parkingRestClient = parkingRestClient;
        this.parkingMapper = parkingMapper;
    }

    @Override
    public List<Parking> getParking(String city) {
        return parkingMapper.toParking(parkingRestClient.fetchRennes());
    }
}

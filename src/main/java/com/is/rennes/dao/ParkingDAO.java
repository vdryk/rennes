package com.is.rennes.dao;

import com.is.rennes.controller.rest.Parking;

import java.util.List;

public interface ParkingDAO {

    List<Parking> getParking(String city);

}

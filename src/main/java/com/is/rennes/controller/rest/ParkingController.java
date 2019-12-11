package com.is.rennes.controller.rest;

import com.is.rennes.dao.ParkingDAO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/parkings")
public class ParkingController {

    private ParkingDAO parkingDAO;

    public ParkingController(ParkingDAO parkingDAO) {
        this.parkingDAO = parkingDAO;
    }

    @GetMapping
    public List<Parking> getShop(@RequestParam String city) {

        return parkingDAO.getParking(city);
    }

}

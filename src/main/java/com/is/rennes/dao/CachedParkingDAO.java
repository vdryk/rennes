package com.is.rennes.dao;

import com.is.rennes.controller.rest.Parking;
import lombok.extern.java.Log;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Profile("cache-enable")
@Primary
@Log
@EnableScheduling
public class CachedParkingDAO implements ParkingDAO {

    private Map<String, List<Parking>> cachedParkings = new HashMap();

    private StraightParkingDAO straightParkingDAO;

    public CachedParkingDAO(StraightParkingDAO straightParkingDAO) {
        this.straightParkingDAO = straightParkingDAO;
        this.cachedParkings.put("Rennes", straightParkingDAO.getParking("Rennes"));
    }

    @Scheduled(fixedRate = 1000)
    private void refresh() {
        log.info("Refreshing parking in cache...");
        // Considering multiple thread access, replacing keys in HashMap is fine
        this.cachedParkings.put("Rennes", this.straightParkingDAO.getParking("Rennes"));
    }

    @Override
    public List<Parking> getParking(String city) {
        return this.cachedParkings.get("Rennes");
    }
}

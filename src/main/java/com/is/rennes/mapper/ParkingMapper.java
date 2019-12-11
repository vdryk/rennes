package com.is.rennes.mapper;

import com.is.rennes.controller.rest.Parking;
import com.is.rennes.restclient.PageRennes;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class ParkingMapper {

    public List<Parking> toParking(List<PageRennes> pageRennesList) {
        return pageRennesList.stream()
                .flatMap(pageRennes -> pageRennes.getRecords().stream())
                .map(record -> Parking.builder()
                        .name(record.getFields().getKey())
                        .spaceLeft(record.getFields().getFree())
                        .latitude(record.getFields().getGeo()[0])
                        .longitude(record.getFields().getGeo()[1])
                        .build())
                .collect(toList());
    }

}

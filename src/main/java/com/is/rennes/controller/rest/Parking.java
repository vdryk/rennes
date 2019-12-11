package com.is.rennes.controller.rest;

import lombok.Builder;
import lombok.Getter;

/**
 * It's... a parking !
 */
@Getter
@Builder
public class Parking {

    private String name;
    private long spaceLeft;
    private double latitude;
    private double longitude;

}

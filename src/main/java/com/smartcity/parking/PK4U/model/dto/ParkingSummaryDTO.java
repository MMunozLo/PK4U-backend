package com.smartcity.parking.PK4U.model.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ParkingSummaryDTO {
    private String id;
    private String name;
    private String address;
    private double latitude;
    private double longitude;

    // Se necesita para el popup del mapa
    private int totalSpots;

    //private int numLevels;
}

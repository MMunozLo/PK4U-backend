package com.smartcity.parking.PK4U.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ParkingDetailsDTO {
    private String id;
    private String name;
    private String address;
    private double latitude;
    private double longitude;
    private double price;
    private int totalSpots;
    private int numLevels;

    // Lista de plantas del parking
    private List<LevelInfoDTO> levelsInfo;
}

package com.smartcity.parking.PK4U.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ParkingDetailsDTO {
    private String id;
    private String name;
    private String address;
    private double latitude;
    private double longitude;
    private double price;
    private int totalSpots;
    private int levels;

    // Lista de plantas del parking
    private List<LevelInfoDTO> levelsInfo;
}

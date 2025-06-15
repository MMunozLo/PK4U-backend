package com.smartcity.parking.PK4U.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpotDTO {
    private String id;
    private String parkingId;
    private int level;
    private int spotNumber;
    private boolean occupied;
}

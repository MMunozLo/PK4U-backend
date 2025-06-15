package com.smartcity.parking.PK4U.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SpotDTO {
    private String id;
    private String parkingId;
    private int level;
    private int spotNumber;
    private boolean occupied;
}

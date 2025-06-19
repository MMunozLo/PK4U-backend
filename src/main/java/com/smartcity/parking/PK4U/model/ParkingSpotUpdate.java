package com.smartcity.parking.PK4U.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class ParkingSpotUpdate implements Serializable {

    private String id;
    private String parkingId;
    private String level; // Added to match Simulator's model
    private boolean occupied;

    public ParkingSpotUpdate(String id, String parkingId, String level, boolean occupied) {
        this.id = id;
        this.parkingId = parkingId;
        this.level = level;
        this.occupied = occupied;
    }


    @Override
    public String toString() {
        return "ParkingSpotUpdate{" +
                "id='" + id + '\'' +
                ", parkingId='" + parkingId + '\'' +
                ", level='" + level + '\'' +
                ", occupied=" + occupied +
                '}';
    }
}

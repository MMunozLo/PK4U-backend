package com.smartcity.parking.PK4U.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@Document(collection = "parking_spots")
public class ParkingSpot {
    @Id
    private String id;
    private String parkingId;
    private int level;
    private int spotNumber;
    private boolean occupied;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }


}

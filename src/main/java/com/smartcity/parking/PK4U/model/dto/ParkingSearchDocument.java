package com.smartcity.parking.PK4U.model.dto;
import lombok.*;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParkingSearchDocument {
    @Id
    private String id;
    private String name;
    private double latitude;
    private double longitude;
    private boolean hasFreeSpots;

}

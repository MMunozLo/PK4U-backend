package com.smartcity.parking.PK4U.model.dto;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "parkings")
public class ParkingSearchDocument {
    @Id
    private String id;
    private String name;
    private double latitude;
    private double longitude;
    private boolean hasFreeSpots;

}

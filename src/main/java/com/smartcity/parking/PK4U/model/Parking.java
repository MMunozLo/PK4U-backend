package com.smartcity.parking.PK4U.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "parkings")
public class Parking {
    @Id
    private String id;
    private String name;
    private String address;
    private double latitude;
    private double longitude;
    private int levels;
}

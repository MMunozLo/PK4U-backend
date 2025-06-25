package com.smartcity.parking.PK4U.model.dto;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "parkingsearchdocument", createIndex = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class ParkingSearchDocument {
    @Id
    private String id;

    @Field(type = FieldType.Text, name = "name")
    private String name;

    @Field(type = FieldType.Double, name = "latitude")
    private Double latitude;

    @Field(type = FieldType.Double, name = "longitude")
    private Double longitude;

    @Field(type = FieldType.Boolean, name = "hasFreeSpots")
    private Boolean hasFreeSpots;

    @Field(type = FieldType.Text, name = "address")
    private String address;

    @Field(type = FieldType.Integer, name = "availableSpots")
    private Integer availableSpots;

    @Field(type = FieldType.Integer, name = "levels")
    private Integer levels;

    @Field(type = FieldType.Double, name = "price")
    private Double price;

}

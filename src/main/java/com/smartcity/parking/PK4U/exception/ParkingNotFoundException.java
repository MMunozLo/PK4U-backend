package com.smartcity.parking.PK4U.exception;


public class ParkingNotFoundException extends RuntimeException {
    public ParkingNotFoundException(String message) {
        super(message);
    }
}


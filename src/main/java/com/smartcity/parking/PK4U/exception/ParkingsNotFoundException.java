package com.smartcity.parking.PK4U.exception;


public class ParkingsNotFoundException extends RuntimeException {
    public ParkingsNotFoundException(String message) {
        super(message);
    }
}


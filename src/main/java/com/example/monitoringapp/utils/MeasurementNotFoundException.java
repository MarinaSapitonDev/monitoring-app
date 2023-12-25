package com.example.monitoringapp.utils;

public class MeasurementNotFoundException extends RuntimeException{

    public MeasurementNotFoundException(String message) {
        super(message);
    }
}

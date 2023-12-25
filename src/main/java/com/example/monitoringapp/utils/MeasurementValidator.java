package com.example.monitoringapp.utils;

import com.example.monitoringapp.models.Measurement;
import org.springframework.stereotype.Component;

@Component
public class MeasurementValidator {
    public boolean isValid(Measurement measurement) {

        return measurement.getId() >= 1 && measurement.getGas() >= 0 && measurement.getColdWater() >= 0
                && measurement.getHotWater() >= 0;
    }
}

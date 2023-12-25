package com.example.monitoringapp.utils;

import com.example.monitoringapp.models.Measurement;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MeasurementValidatorTest {

    @Test
    void testMeasurementValid(){

        Measurement measurement = new Measurement(1,1,10,20,30);

        MeasurementValidator validator = new MeasurementValidator();

        assertThat(validator.isValid(measurement)).isEqualTo(true);
    }

    @Test
    void testMeasurementNotValid(){
        Measurement measurement = new Measurement(1,0,10,-20,30);

        MeasurementValidator validator = new MeasurementValidator();

        assertThat(validator.isValid(measurement)).isEqualTo(false);
    }
}
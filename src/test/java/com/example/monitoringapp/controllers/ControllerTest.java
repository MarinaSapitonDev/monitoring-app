package com.example.monitoringapp.controllers;

import com.example.monitoringapp.models.Measurement;
import com.example.monitoringapp.models.Response;
import com.example.monitoringapp.services.MeasurementService;
import com.example.monitoringapp.utils.MeasurementNotFoundException;
import com.example.monitoringapp.utils.MeasurementValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ControllerTest {

    @Mock
    MeasurementValidator validator;

    @Mock
    MeasurementService service;

    @Mock
    Response response;

    @InjectMocks
    Controller controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSubmitMeasurementsValid() {
        Measurement measurement = new Measurement();
        when(validator.isValid(measurement)).thenReturn(true);

        ResponseEntity<Response> responseEntity = controller.submitMeasurements(measurement);

        verify(service, times(1)).save(measurement);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    void testSubmitMeasurementsNotValid() {
        Measurement measurement = new Measurement();
        when(validator.isValid(measurement)).thenReturn(false);

        ResponseEntity<Response> responseEntity = controller.submitMeasurements(measurement);

        verify(service, times(0)).save(measurement);
        assertThat(HttpStatus.BAD_REQUEST ).isEqualTo(responseEntity.getStatusCode());
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(400);
    }

    @Test
    void testMeasurementsHistoryFound(){
        int userId = 1;
        List<Measurement> measurements = Collections.singletonList(new Measurement());
        when(service.findByUserId(userId)).thenReturn(measurements);

        ResponseEntity<List<Measurement>> responseEntity = controller.measurementsHistory(userId);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(measurements);
    }

    @Test
    void testMeasurementsHistoryNotFound(){
        int userId = 2;
        List<Measurement> measurements = Collections.emptyList();
        when(service.findByUserId(userId)).thenReturn(measurements);

        MeasurementNotFoundException exception = assertThrows(
                MeasurementNotFoundException.class,
                () -> controller.measurementsHistory(userId)
        );

        assertThat(exception.getMessage()).isEqualTo("History for user with id = 2 not found");
    }

    @Test
    void testHandleException() {
        MeasurementNotFoundException exception = new MeasurementNotFoundException("Not found");

        ResponseEntity<Response> responseEntity = controller.handleException(exception);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(404);
    }
}
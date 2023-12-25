package com.example.monitoringapp.controllers;

import com.example.monitoringapp.models.Measurement;
import com.example.monitoringapp.models.Response;
import com.example.monitoringapp.services.MeasurementService;
import com.example.monitoringapp.utils.MeasurementNotFoundException;
import com.example.monitoringapp.utils.MeasurementValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class Controller {

    private final MeasurementService measurementService;
    private final Response response;
    private final MeasurementValidator validator;

    @Autowired
    public Controller(MeasurementService measurementService, Response response, MeasurementValidator validator) {
        this.measurementService = measurementService;
        this.response = response;
        this.validator = validator;
    }

    @PostMapping("/submit")
    public ResponseEntity<Response> submitMeasurements(@RequestBody Measurement measurement) {

        if(!validator.isValid(measurement)){
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setDescription("Invalid measurement data");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        measurementService.save(measurement);
        response.setDescription("Measurement saved successfully");
        response.setStatus(HttpStatus.CREATED);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/history/{userId}")
    public ResponseEntity<List<Measurement>> measurementsHistory(@PathVariable int userId) {

        List<Measurement> measurement = measurementService.findByUserId(userId);
        if (measurement.isEmpty()) {
            throw new MeasurementNotFoundException("History for user with id = " + userId + " not found");
        }

        return new ResponseEntity<>(measurement, HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<Response> handleException(MeasurementNotFoundException exception) {
        response.setStatus(HttpStatus.NOT_FOUND);
        response.setDescription("Measurement for the given user id not found. The measurement might not be created");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}

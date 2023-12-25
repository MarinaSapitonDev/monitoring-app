package com.example.monitoringapp.services;

import com.example.monitoringapp.models.Measurement;
import com.example.monitoringapp.repository.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeasurementService {

    private final MeasurementRepository measurementRepository;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }

    public void save(Measurement measurement){
        measurementRepository.save(measurement);
    }

    public List<Measurement> findByUserId(int userId){
        return measurementRepository.findByUserId(userId);
    }
}

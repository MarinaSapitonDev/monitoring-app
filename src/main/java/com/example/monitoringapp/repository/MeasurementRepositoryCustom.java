package com.example.monitoringapp.repository;

import com.example.monitoringapp.models.Measurement;

import java.util.List;

public interface MeasurementRepositoryCustom {
    List<Measurement> findByUserId(int userId);
}

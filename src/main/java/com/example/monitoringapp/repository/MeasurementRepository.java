package com.example.monitoringapp.repository;

import com.example.monitoringapp.models.Measurement;
import org.springframework.data.repository.CrudRepository;

public interface MeasurementRepository
        extends CrudRepository<Measurement, Integer>, MeasurementRepositoryCustom {

}

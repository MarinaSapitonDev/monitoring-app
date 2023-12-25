package com.example.monitoringapp.repository;

import com.example.monitoringapp.models.Measurement;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class MeasurementRepositoryCustomImpl implements MeasurementRepositoryCustom{
    @PersistenceContext
    private EntityManager entityManager;

    public List<Measurement> findByUserId(int userId) {
        Query query = entityManager.createNativeQuery("select * from PUBLIC.MEASURMENTS_TBL where user_id = ?", Measurement.class);
        query.setParameter(1, userId);
        return query.getResultList();
    }
}

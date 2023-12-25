package com.example.monitoringapp.repository;

import com.example.monitoringapp.models.Measurement;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
class MeasurementRepositoryImplTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private MeasurementRepository measurementRepository;

    @Test
    void testFindByUserId() {
        int userId = 1;

        entityManager.persist(new Measurement(1, userId, 100, 125, 300));
        entityManager.persist(new Measurement(2, userId, 150, 200, 400));

        List<Measurement> result= measurementRepository.findByUserId(userId);

        assertFalse(result.isEmpty());
        assertEquals(2, result.size());
        assertEquals(result.get(0).getUserId(), 1);
        assertEquals(result.get(1).getColdWater(), 200);
    }
}
package com.example.monitoringapp.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@NoArgsConstructor

@Entity
@Table(name="measurments_tbl")
public class Measurement {

    public Measurement(int id, int userId, int gas, int coldWater, int hotWater) {
        this.id = id;
        this.userId = userId;
        this.gas = gas;
        this.coldWater = coldWater;
        this.hotWater = hotWater;
    }

    @Id
    @Column(name="id")
    private int id;

    @Column(name="user_id")
    private int userId;

    @Column(name="gas")
    private int gas;

    @Column(name="cold_water")
    private int coldWater;

    @Column(name="hot_water")
    private int hotWater;
}

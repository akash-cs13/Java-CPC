package com.fhdo.entities;

public class WaitingCar {
    private Car car;
    private EnergySource energySource;

    public WaitingCar(Car car, EnergySource energySource) {
        this.car = car;
        this.energySource = energySource;
    }

    public Car getCar() {
        return car;
    }

    public EnergySource getEnergySource() {
        return energySource;
    }
}
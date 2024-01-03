package com.fhdo.entities.cars;

public class WaitingCar {
    private Car car;
    //private energySources energySources;

    public WaitingCar(Car car) {
        this.car = car;
        //this.energySources = energySources;
    }

    public Car getCar() {
        return car;
    }

//    public energySources getenergySources() {
//        return energySources;
//    }
}
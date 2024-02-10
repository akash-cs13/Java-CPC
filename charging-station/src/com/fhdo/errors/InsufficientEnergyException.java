package com.fhdo.errors;

public class InsufficientEnergyException extends RuntimeException {
    public InsufficientEnergyException() {
        super("Unable to charge the car due to insufficient energy.");
    }

}
package com.fhdo.controller;

public class ChargingException extends Exception {
    public ChargingException(final String message) {
        super(message);
    }

    public ChargingException(final String message, final Exception root) {
        super(message, root);
    }
}

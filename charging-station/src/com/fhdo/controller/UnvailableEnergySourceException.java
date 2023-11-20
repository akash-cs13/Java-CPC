package com.fhdo.controller;

public class UnvailableEnergySourceException extends Exception {
    private static final long serialVersionUID = 1L;

   public  UnvailableEnergySourceException(final String message) {
        super(message);
    }

    public UnvailableEnergySourceException(final String message, final Exception root) {
        super(message, root);
    }
}


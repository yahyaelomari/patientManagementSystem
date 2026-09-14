package com.patientmanagementsystem.patient.exception;

public class PatientDataAccessException extends RuntimeException {

    public PatientDataAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}

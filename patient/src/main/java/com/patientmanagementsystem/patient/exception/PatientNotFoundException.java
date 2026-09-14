package com.patientmanagementsystem.patient.exception;

import java.util.UUID;

public class PatientNotFoundException extends RuntimeException {

    public PatientNotFoundException(UUID id) {
        super("No patient found with id " + id);
    }

    public PatientNotFoundException(String message) {
        super(message);
    }
}

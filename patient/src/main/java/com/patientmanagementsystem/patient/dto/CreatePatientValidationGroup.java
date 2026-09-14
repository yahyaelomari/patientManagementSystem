package com.patientmanagementsystem.patient.dto;

/**
 * Validation group applied only when creating a patient.
 * Fields marked with this group (e.g. dateofBirth, registeredDate) are
 * required on create but not enforced on update, where they may be omitted.
 */
public interface CreatePatientValidationGroup {
}

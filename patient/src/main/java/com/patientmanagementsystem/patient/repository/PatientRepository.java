package com.patientmanagementsystem.patient.repository;

import com.patientmanagementsystem.patient.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PatientRepository extends JpaRepository<Patient, UUID> {
 Boolean existsPatientsByMail(String mail);

 Boolean existsPatientsByMailAndIdNot(String mail, UUID id);

}

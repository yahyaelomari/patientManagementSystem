package com.patientmanagementsystem.patient.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientRequestDto {


    @NotBlank
    @Size(max=100, message="Name cannot exceed 100 chars")
    private String name;

    @NotBlank(message="Email is required!")
    @Email(message="Email should be valid!")
    private String mail;

    @NotBlank(message="Adress is required!")
    private String adress;

    @NotNull(message="Date of birth is required!", groups = CreatePatientValidationGroup.class)
    private LocalDate dateofBirth;

    @NotNull(message="Registered date is required!", groups = CreatePatientValidationGroup.class)
    private LocalDate registeredDate ;

}

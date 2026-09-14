package com.patientmanagementsystem.patient.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class PatientResponseDto{


    @NotNull
    @Column(unique = true)
    private UUID id;

    @NonNull
    private String name;

    @NotNull
    @Email
    @Column(unique = true)
    private String mail;

    @NotNull
    private String adress;

    @NotNull
    private LocalDate dateofBirth;

    @NotNull
    private LocalDate registeredDate ;

}
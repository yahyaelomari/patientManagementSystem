package com.patientmanagementsystem.patient.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NonNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Email
    @Column(name = "email", unique = true)
    private String mail;

    @NotNull
    @Column(name = "address")
    private String adress;

    @NotNull
    @Column(name = "date_of_birth")
    private LocalDate dateofBirth;

    @NotNull
    @Column(name = "registered_date")
    private LocalDate registeredDate ;

}

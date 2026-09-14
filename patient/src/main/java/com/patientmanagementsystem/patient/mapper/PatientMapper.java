package com.patientmanagementsystem.patient.mapper;

import com.patientmanagementsystem.patient.dto.PatientRequestDto;
import com.patientmanagementsystem.patient.model.Patient;
import com.patientmanagementsystem.patient.dto.PatientResponseDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PatientMapper {

    public static PatientResponseDto toDto(Patient patient){
         return PatientResponseDto.builder()
                 .id(patient.getId())
                 .name(patient.getName())
                 .mail(patient.getMail())
                 .adress(patient.getAdress())
                 .dateofBirth(patient.getDateofBirth())
                 .registeredDate(patient.getRegisteredDate())
                 .build();
    }

    public static Patient toModel(PatientRequestDto patientRequestDto){
        return Patient.builder()
                .name(patientRequestDto.getName())
                .mail(patientRequestDto.getMail())
                .adress(patientRequestDto.getAdress())
                .dateofBirth(patientRequestDto.getDateofBirth())
                .registeredDate(patientRequestDto.getRegisteredDate())
                .build();
    }

}

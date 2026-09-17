package com.patientmanagementsystem.patient.service;


import com.patientmanagementsystem.patient.dto.PatientRequestDto;
import com.patientmanagementsystem.patient.exception.EmailAlreadyExistsException;
import com.patientmanagementsystem.patient.exception.PatientDataAccessException;
import com.patientmanagementsystem.patient.exception.PatientNotFoundException;
import com.patientmanagementsystem.patient.grpc.BillingServiceGrpcClient;
import com.patientmanagementsystem.patient.mapper.PatientMapper;
import com.patientmanagementsystem.patient.dto.PatientResponseDto;
import com.patientmanagementsystem.patient.model.Patient;
import com.patientmanagementsystem.patient.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final BillingServiceGrpcClient billingServiceGrpcClient;

    @Transactional(readOnly = true)
    public List<PatientResponseDto> getAllPatients(){
        try {
            return this.patientRepository.findAll()
                    .stream()
                    .map(PatientMapper::toDto)
                    .toList();
        } catch (DataAccessException ex) {
            throw new PatientDataAccessException("Could not load the patient list", ex);
        }
    }

    @Transactional
    public PatientResponseDto createPatient(PatientRequestDto patientRequest){
            if(patientRepository.existsPatientsByMail(patientRequest.getMail())){
                throw new EmailAlreadyExistsException("A patient with this mail exists already");
            };

            Patient patient = patientRepository.save(PatientMapper.toModel(patientRequest));

            billingServiceGrpcClient.createBillingAccount(patient.getId().toString(),patient.getName(),patient.getMail());

            return PatientMapper.toDto(patient);

    }

    @Transactional
    public PatientResponseDto updatePatient(UUID id, PatientRequestDto patientRequestDto){
        Patient patientToUpdate = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException(id));

        if(patientRepository.existsPatientsByMailAndIdNot(patientRequestDto.getMail(),id)){
            throw new EmailAlreadyExistsException("A patient with this mail exists already");
        }

        patientToUpdate.setName(patientRequestDto.getName());
        patientToUpdate.setMail(patientRequestDto.getMail());
        patientToUpdate.setAdress(patientRequestDto.getAdress());
        if (patientRequestDto.getDateofBirth() != null) {
            patientToUpdate.setDateofBirth(patientRequestDto.getDateofBirth());
        }
        if (patientRequestDto.getRegisteredDate() != null) {
            patientToUpdate.setRegisteredDate(patientRequestDto.getRegisteredDate());
        }

        Patient saved = patientRepository.save(patientToUpdate);
        return PatientMapper.toDto(saved);
    }

    @Transactional
    public void deletePatient(UUID id){
        if (!patientRepository.existsById(id)) {
            throw new PatientNotFoundException(id);
        }
        patientRepository.deleteById(id);
    }


}

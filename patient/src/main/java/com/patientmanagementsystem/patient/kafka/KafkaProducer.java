package com.patientmanagementsystem.patient.kafka;

import com.patientmanagementsystem.patient.model.Patient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import patient.events.PatientEvent;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducer {

    private final KafkaTemplate<String,byte[]> kafkaTemplate;


    public void sendEvent(Patient patient){
        PatientEvent event = PatientEvent.newBuilder()
                .setPatientId(patient.getId().toString())
                .setEmail(patient.getMail())
                .setEventType("PATIENT_CREATED")
                .build();

        try{
            kafkaTemplate.send("patient", event.toByteArray());
        }catch (Exception ex){
            log.error("Error sending patient kafka event: {}",ex.getMessage());
        }

    }



}

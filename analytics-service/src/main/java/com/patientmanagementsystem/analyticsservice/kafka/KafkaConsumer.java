package com.patientmanagementsystem.analyticsservice.kafka;


import com.google.protobuf.InvalidProtocolBufferException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import patient.events.PatientEvent;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumer {


    @KafkaListener(topics = "patient", groupId = "analytics-service")
    public void consumeEvent(byte[] event){

        try {
            PatientEvent patientEvent = PatientEvent.parseFrom(event);
            //analytics business logic...
            log.info("received patient id : {}, pateint email: {}, patient event type:{}", patientEvent.getPatientId(), patientEvent.getEmail(), patientEvent.getEventType());
        } catch (InvalidProtocolBufferException e) {
            log.error("Could not parse the patient event while consuming it {}", e.getMessage());
        }
    }
}

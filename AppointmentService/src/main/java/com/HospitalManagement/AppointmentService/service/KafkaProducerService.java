package com.HospitalManagement.AppointmentService.service;

import com.HospitalManagement.AppointmentService.dtos.AppointentDTO;
import com.sun.source.doctree.SeeTree;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
    private final KafkaTemplate<String,Object> kafkaTemplate;
    private static final String TOPIC_APPOINTMENT_CREATED = "appointment-created";

    public KafkaProducerService(KafkaTemplate<String,Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishAppointmentCreated(AppointentDTO appointentDTO){
        kafkaTemplate.send(TOPIC_APPOINTMENT_CREATED,appointentDTO);
    }
}

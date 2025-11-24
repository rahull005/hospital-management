package com.HospitalManagement.AppointmentService.service;

import com.HospitalManagement.AppointmentService.dtos.AppointentDTO;
import com.HospitalManagement.AppointmentService.entities.Appointment;
import com.HospitalManagement.AppointmentService.repository.AppointmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@Slf4j
public class AppointmentService {
    private KafkaProducerService producerService;
    private AppointmentRepository appointmentRepository;
    @Autowired
    private WebClient.Builder webclient;

    public AppointmentService(KafkaProducerService producerService,AppointmentRepository appointmentRepository){
        this.producerService = producerService;
        this.appointmentRepository = appointmentRepository;
    }



    public Appointment create(Appointment appointment){
        appointment.setCreatedAt(LocalDateTime.now());
        Appointment saved = appointmentRepository.save(appointment);

        log.info("inside create method in appointment service");

        //getemails
        String doctorEmail = webclient
                .baseUrl("http://DOCTORSERVICE")
                .build()
                .get()
                .uri("/doctor/email/"+appointment.getDoctorId())
                .retrieve()
                .bodyToMono(String.class)
                .block();

        log.info("doctor email : {}", doctorEmail);

        String patientEmail = webclient
                .baseUrl("http://PATIENTSERVICE")
                .build()
                .get()
                .uri("/patient/email/"+appointment.getPatientId())
                .retrieve()
                .bodyToMono(String.class)
                .block();

        log.info("patient email : {}", patientEmail);


        AppointentDTO dto = new AppointentDTO();
        dto.setId(saved.getId());
        dto.setPatientId(saved.getPatientId());
        dto.setDoctorId(saved.getDoctorId());
        dto.setAppointmentTime(saved.getAppointmentTime());
        dto.setPurpose(saved.getPurpose());
        dto.setCreatedBy(saved.getCreatedBy());
        dto.setCreatedRoles(saved.getCreatedRoles());
        dto.setDoctorEmail(doctorEmail);
        dto.setPatientEmail(patientEmail);



        producerService.publishAppointmentCreated(dto);
        return saved;

    }
}

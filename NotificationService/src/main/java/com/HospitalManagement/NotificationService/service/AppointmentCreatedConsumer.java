package com.HospitalManagement.NotificationService.service;

import com.HospitalManagement.NotificationService.dtos.AppointmentDTO;
import com.HospitalManagement.NotificationService.model.NotificationAppointment;
import com.HospitalManagement.NotificationService.repository.NotificationRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AppointmentCreatedConsumer {
    private final NotificationRepository notificationRepository;
    private final EmailService emailService;

    public AppointmentCreatedConsumer(NotificationRepository notificationRepository,EmailService emailService){
        this.notificationRepository = notificationRepository;
        this.emailService = emailService;
    }

    @KafkaListener(topics = "appointment-created", groupId = "notification-service-group")
    public void consume(AppointmentDTO event) {

        NotificationAppointment n = new NotificationAppointment();
        n.setAppointmentId(event.getId());
        n.setPatientId(event.getPatientId());
        n.setDoctorId(event.getDoctorId());
        n.setPatientEmail(event.getPatientEmail());
        n.setDoctorEmail(event.getDoctorEmail());
        n.setPurpose(event.getPurpose());
        n.setAppointmentTime(event.getAppointmentTime());
        n.setCreatedAt(LocalDateTime.now());

        notificationRepository.save(n);

        // Send confirmation email
        if (event.getPatientEmail() != null) {
            emailService.sendAppointmentConfirmation(event.getPatientEmail(), event);
        }
    }
}

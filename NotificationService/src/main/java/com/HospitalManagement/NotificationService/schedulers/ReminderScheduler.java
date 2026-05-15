package com.HospitalManagement.NotificationService.schedulers;


import com.HospitalManagement.NotificationService.dtos.AppointmentDTO;
import com.HospitalManagement.NotificationService.model.NotificationAppointment;
import com.HospitalManagement.NotificationService.repository.NotificationRepository;
import com.HospitalManagement.NotificationService.service.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ReminderScheduler {

    private final NotificationRepository repo;
    private final EmailService emailService;

    // How many hours before the appointment should the reminder be sent?
    @Value("${notification.reminder.hours-before:24}")
    private int hoursBefore;

    public ReminderScheduler(NotificationRepository repo, EmailService emailService) {
        this.repo = repo;
        this.emailService = emailService;
    }

    /**
     * Scheduler runs every configurable delay (default: 5 minutes)
     * Initial delay: 10 seconds
     *
     * Uses ISO-8601 duration values like PT5M, PT10S
     */
    @Scheduled(
            fixedDelayString = "${notification.reminder.check-interval:PT5M}",
            initialDelayString = "${notification.reminder.initial-delay:PT10S}"
    )
    public void sendReminders() {

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime target = now.plusHours(hoursBefore);

        List<NotificationAppointment> appointments =
                repo.findByReminderSentFalseAndAppointmentTimeBetween(now, target);

        for (NotificationAppointment appt : appointments) {
            try {
                emailService.sendAppointmentReminder(
                        appt.getPatientEmail(),
                        convert(appt)
                );
                appt.setReminderSent(true);
                repo.save(appt);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private AppointmentDTO convert(NotificationAppointment appt) {
        AppointmentDTO dto = new AppointmentDTO();
        dto.setId(appt.getAppointmentId());
        dto.setPatientId(appt.getPatientId());
        dto.setDoctorId(appt.getDoctorId());
        dto.setPatientEmail(appt.getPatientEmail());
        dto.setDoctorEmail(appt.getDoctorEmail());
        dto.setPurpose(appt.getPurpose());
        dto.setAppointmentTime(appt.getAppointmentTime());
        return dto;
    }
}

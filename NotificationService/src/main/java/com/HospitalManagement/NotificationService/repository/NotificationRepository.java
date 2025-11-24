package com.HospitalManagement.NotificationService.repository;

import com.HospitalManagement.NotificationService.model.NotificationAppointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface NotificationRepository extends JpaRepository<NotificationAppointment,Integer> {
    List<NotificationAppointment> findByReminderSentFalseAndAppointmentTimeBetween(
            LocalDateTime start,
            LocalDateTime end
    );
}

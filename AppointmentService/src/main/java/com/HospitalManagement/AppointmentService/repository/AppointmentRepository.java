package com.HospitalManagement.AppointmentService.repository;

import com.HospitalManagement.AppointmentService.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment,Integer> {
    List<Appointment> findByAppointmentTimeBetween(LocalDateTime from, LocalDateTime to);

}

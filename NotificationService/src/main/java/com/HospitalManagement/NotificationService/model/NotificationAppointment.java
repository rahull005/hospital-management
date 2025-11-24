package com.HospitalManagement.NotificationService.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Entity
@Table(name = "hm_notification_appointment")
public class NotificationAppointment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq")
    @SequenceGenerator(name = "seq",sequenceName = "seq",allocationSize = 1)
    private Long appointmentId;

    private Long patientId;
    private Long doctorId;

    private String patientEmail;
    private String doctorEmail;

    private String purpose;

    private LocalDateTime appointmentTime;

    private boolean reminderSent = false;
    private boolean confirmationSent = false;

    private LocalDateTime createdAt;

    public NotificationAppointment() {
    }

    public NotificationAppointment(Long appointmentId, Long patientId, Long doctorId, String patientEmail, String doctorEmail, String purpose, LocalDateTime appointmentTime, boolean reminderSent, boolean confirmationSent, LocalDateTime createdAt) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.patientEmail = patientEmail;
        this.doctorEmail = doctorEmail;
        this.purpose = purpose;
        this.appointmentTime = appointmentTime;
        this.reminderSent = reminderSent;
        this.confirmationSent = confirmationSent;
        this.createdAt = createdAt;
    }

    public Long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public String getPatientEmail() {
        return patientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
    }

    public String getDoctorEmail() {
        return doctorEmail;
    }

    public void setDoctorEmail(String doctorEmail) {
        this.doctorEmail = doctorEmail;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public LocalDateTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalDateTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public boolean isReminderSent() {
        return reminderSent;
    }

    public void setReminderSent(boolean reminderSent) {
        this.reminderSent = reminderSent;
    }

    public boolean isConfirmationSent() {
        return confirmationSent;
    }

    public void setConfirmationSent(boolean confirmationSent) {
        this.confirmationSent = confirmationSent;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

package com.HospitalManagement.AppointmentService.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "hm_appointment")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq")
    @SequenceGenerator(name = "seq",sequenceName = "seq",allocationSize = 1)
    private int id;
    private int patientId;
    private int doctorId;
    private LocalDateTime appointmentTime;
    private String purpose;
    private String createdBy;
    private String createdRoles;
    private LocalDateTime createdAt;
    private boolean reminderSent = false;

    public Appointment() {
    }

    public Appointment(int id, int patientId, int doctorId, LocalDateTime appointmentTime, String purpose, String createdBy, String createdRoles, LocalDateTime createdAt, boolean reminderSent) {
        this.id = id;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.appointmentTime = appointmentTime;
        this.purpose = purpose;
        this.createdBy = createdBy;
        this.createdRoles = createdRoles;
        this.createdAt = createdAt;
        this.reminderSent = reminderSent;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public LocalDateTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalDateTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }


    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedRoles() {
        return createdRoles;
    }

    public void setCreatedRoles(String createdRoles) {
        this.createdRoles = createdRoles;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isReminderSent() {
        return reminderSent;
    }

    public void setReminderSent(boolean reminderSent) {
        this.reminderSent = reminderSent;
    }
}

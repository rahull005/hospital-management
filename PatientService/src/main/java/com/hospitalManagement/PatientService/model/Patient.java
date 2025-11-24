package com.hospitalManagement.PatientService.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "hm_patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq")
    @SequenceGenerator(name = "seq",sequenceName = "seq",allocationSize = 1)
    @Column(name = "patient_id")
    private int id;

    @Column(name = "patient_name", nullable = false)
    private String username;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "phone", nullable = false, unique = true)
    private String phone;

    @Column(name = "dob")
    private LocalDate dob;

    @Column(name = "gender")
    private String gender;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_roles")
    private String createdRoles;

    @Column(name = "created_date")
    private LocalDate createdDate;

    public Patient() {
    }

    public Patient(int id, String username, String email, String phone, LocalDate dob, String gender, String createdBy, String createdRoles, LocalDate createdDate) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.dob = dob;
        this.gender = gender;
        this.createdBy = createdBy;
        this.createdRoles = createdRoles;
        this.createdDate = createdDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", name='" + username + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", dob=" + dob +
                ", gender='" + gender + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", createdRoles='" + createdRoles + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }
}

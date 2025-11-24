package com.hospitalManagement.Docterservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "hm_docter")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq")
    @SequenceGenerator(name = "seq",sequenceName = "seq",allocationSize = 1)
    private int id;
    @Column(name = "docter_name")
    private String username;

    @Column(name = "specialization")
    private String specialization;

    @Column(name = "email")
    private String email;

    @Column(name = "mobile_no")
    private String phone;

    @Column(name = "No_of_years_of_experience")
    private int yeasOfExperience;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_roles")
    private String createdRoles;

    @Column(name = "created_date")
    private LocalDate createDate;

    public Doctor(int id, String username, String specialization, String email, String phone, int yeasOfExperience, String createdBy, String createdRoles, LocalDate createDate) {
        this.id = id;
        this.username = username;
        this.specialization = specialization;
        this.email = email;
        this.phone = phone;
        this.yeasOfExperience = yeasOfExperience;
        this.createdBy = createdBy;
        this.createdRoles = createdRoles;
        this.createDate = createDate;
    }

    public Doctor() {
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

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
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

    public int getYeasOfExperience() {
        return yeasOfExperience;
    }

    public void setYeasOfExperience(int yeasOfExperience) {
        this.yeasOfExperience = yeasOfExperience;
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

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }
}

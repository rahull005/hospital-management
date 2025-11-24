package com.hospitalManagement.Docterservice.repository;

import com.hospitalManagement.Docterservice.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepo extends JpaRepository<Doctor,Integer> {
}

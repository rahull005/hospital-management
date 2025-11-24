package com.hospitalManagement.PatientService.repository;

import com.hospitalManagement.PatientService.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepo extends JpaRepository<Patient,Integer> {
    Optional<Patient> findByUsername(String username);
}

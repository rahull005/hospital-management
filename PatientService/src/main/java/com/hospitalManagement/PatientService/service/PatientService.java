package com.hospitalManagement.PatientService.service;

import com.hospitalManagement.PatientService.model.Patient;
import com.hospitalManagement.PatientService.repository.PatientRepo;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    private final PatientRepo patientRepo;
    public PatientService(PatientRepo patientRepo){
        this.patientRepo = patientRepo;
    }

    public Patient register(Patient patient, String username,String roles){
        patient.setCreatedBy(username);
        patient.setCreatedRoles(roles);
        patient.setCreatedDate(LocalDate.now());
        return patientRepo.save(patient);
    }

    public Optional<Patient> getPatientById(int id){
        return patientRepo.findById(id);
    }

    public List<Patient> getAllPatientList(){
        return patientRepo.findAll();
    }

    public Patient updatePatientDetails(int id, Patient patient, String username,String roles){
        return patientRepo.findById(id)
                .map(existing -> {
                    existing.setUsername(patient.getUsername());
                    existing.setEmail(patient.getEmail());
                    existing.setPhone(patient.getPhone());
                    existing.setDob(patient.getDob());
                    existing.setGender(patient.getGender());
                    existing.setCreatedBy(username);
                    existing.setCreatedRoles(roles);
                    existing.setCreatedDate(LocalDate.now());
                    return patientRepo.save(existing);
                }).orElseThrow(() -> new RuntimeException("Patient not found"));
    }

    public void deletePatientById(int id){
        patientRepo.deleteById(id);
    }

}

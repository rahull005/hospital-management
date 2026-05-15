package com.hospitalManagement.PatientService.controller;
import com.hospitalManagement.PatientService.model.Patient;
import com.hospitalManagement.PatientService.repository.PatientRepo;
import com.hospitalManagement.PatientService.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {

    private final PatientService patientService;
    private final PatientRepo patientRepo;

    public PatientController(PatientService patientService,PatientRepo patientRepo) {
        this.patientService = patientService;
        this.patientRepo = patientRepo;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerPatient(
            @RequestBody Patient patient,
            @RequestHeader("X-User") String username,
            @RequestHeader("X-Roles") String roles) {

        if(patientRepo.findByUsername(patient.getUsername()).isPresent()){
            return ResponseEntity.badRequest().body("Patient name already exist !! try with adding first or last name ");
        }

        if (!roles.contains("ROLE_ADMIN") && !roles.contains("ROLE_RECEPTIONIST")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Access denied: Only ADMIN or RECEPTIONIST can register a patient");
        }

        return ResponseEntity.ok(patientService.register(patient, username, roles));
    }

    @GetMapping("/getbyid/{id}")
    public ResponseEntity<?> getPatientById(
            @PathVariable int id,
            @RequestHeader("X-Roles") String roles) {

        if (!roles.contains("ROLE_ADMIN") && !roles.contains("ROLE_DOCTOR")) {
            throw new RuntimeException("Access denied: Only ADMIN or DOCTOR can view patient details");
        }

//        Patient patient = patientService.getPatientById(id)
//                .orElseThrow(() -> new RuntimeException("Patient not found with id: " + id));
        if(!patientRepo.findById(id).isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("USER WITH GIVEN ID DOES NOT EXIST");
        }

        return ResponseEntity.ok(patientService.getPatientById(id));
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAllPatients(@RequestHeader("X-Roles") String roles) {
        if (!roles.contains("ROLE_ADMIN") && !roles.contains("ROLE_DOCTOR")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Access denied: Only ADMIN or DOCTOR can view all patients");
        }
        List<Patient> patients = patientService.getAllPatientList();
        return ResponseEntity.ok(patients);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updatePatient(
            @PathVariable int id,
            @RequestBody Patient updatedPatient,
            @RequestHeader("X-User") String username,
            @RequestHeader("X-Roles") String roles) {

        if (!roles.contains("ROLE_ADMIN") && !roles.contains("ROLE_DOCTOR")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Access denied: Only ADMIN or DOCTOR can update patient info");
        }

        return ResponseEntity.ok(patientService.updatePatientDetails(id, updatedPatient, username, roles));
    }

    @DeleteMapping("/deletebyid/{id}")
    public ResponseEntity<?> deletePatient(
            @PathVariable int id,
            @RequestHeader("X-Roles") String roles) {

        if (!roles.contains("ROLE_ADMIN")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Access denied: Only ADMIN can delete patients");
        }

        patientService.deletePatientById(id);
        return ResponseEntity.ok("Patient deleted successfully");
    }


    @GetMapping("/email/{id}")
    public String getEmail(@PathVariable int id){
        return patientRepo.findById(id).map(obj->obj.getEmail()).orElseThrow(()->new UsernameNotFoundException("User Not Found with given id"));
    }
}
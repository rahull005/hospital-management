package com.hospitalManagement.Docterservice.controller;

import com.hospitalManagement.Docterservice.model.Doctor;
import com.hospitalManagement.Docterservice.repository.DoctorRepo;
import com.hospitalManagement.Docterservice.service.DoctorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/doctor")
@Slf4j
public class DoctorController {
    private final DoctorService doctorService;
    private final DoctorRepo doctorRepo;
    public DoctorController(DoctorService doctorService,DoctorRepo doctorRepo){
        this.doctorService = doctorService;
        this.doctorRepo = doctorRepo;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerDocter(@RequestBody Doctor doctor,
                                            @RequestHeader("X-User") String username,
                                            @RequestHeader("X-Roles") String roles){

        log.info("Entered into doctor registration method for registration");
        if(!roles.contains("ROLE_ADMIN")){
            log.info("Failed to add docter");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("YOU DONT HAVE PERMISSION TO SAVE REGISTER THE DOCTOR");
        }

        return ResponseEntity.ok(doctorService.registerDoctor(doctor,username,roles));
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getallDoctorList(){
        if(doctorRepo.findAll().isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NO DOCTERS FOUND");
        }
        return ResponseEntity.ok(doctorService.getAllDocters());
    }

    @GetMapping("/getbyid/{id}")
    public ResponseEntity<?> getbyid(@PathVariable int id){
        if(doctorRepo.findById(id).isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with the given id : "+id);
        }
        return ResponseEntity.status(HttpStatus.OK).body(doctorService.getById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateDoctorDetails(@PathVariable int id,
                                                 @RequestBody Doctor doctor,
                                                 @RequestHeader("X-User") String username,
                                                 @RequestHeader("X-Roles") String roles
                                                 ){
        if(doctorRepo.findById(id).isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with the give id : "+id);
        }
         return ResponseEntity.status(HttpStatus.OK).body(doctorService.updateDocterDetails(id,doctor,username,roles));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable int id){
        if(doctorRepo.findById(id).isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with the give id : "+id);
        }

        return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully with the given id : "+id);
    }


    @GetMapping("/email/{id}")
    public ResponseEntity<?> getEmailById(@PathVariable int id){

        Optional<Doctor> doctor = doctorRepo.findById(id);
        return doctor.map(d->ResponseEntity.ok(d.getEmail())).orElseThrow(()->new UsernameNotFoundException("User not found"));
    }

}

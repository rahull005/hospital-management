package com.hospitalManagement.Docterservice.service;

import com.hospitalManagement.Docterservice.model.Doctor;
import com.hospitalManagement.Docterservice.repository.DoctorRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class DoctorService {
    private final DoctorRepo doctorRepository;

    public DoctorService(DoctorRepo doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public Doctor registerDoctor(Doctor doctor, String username, String roles) {
        doctor.setCreatedBy(username);
        doctor.setCreatedRoles(roles);
        doctor.setCreateDate(LocalDate.now());
        log.info("in the docter service for registration");
        return doctorRepository.save(doctor);
    }

    public List<Doctor> getAllDocters(){
        return doctorRepository.findAll();
    }

    public Doctor getById(int id){
        return doctorRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("No docter found with the following id : "+id));
    }

    public String deleteDocter(int id){
        if(doctorRepository.findById(id).isPresent()){
            doctorRepository.deleteById(id);
            return "User deleted successfully";
        }
        throw new UsernameNotFoundException("NO DOCTOR FOUND WITH THE GIVEN ID : "+id);
    }

    public Doctor updateDocterDetails(int id,Doctor doctor,String username,String roles){
        return doctorRepository.findById(id)
                .map(present->{
                    present.setUsername(doctor.getUsername());
                    present.setPhone(doctor.getEmail());
                    present.setPhone(doctor.getPhone());
                    present.setSpecialization(doctor.getSpecialization());
                    present.setYeasOfExperience(doctor.getYeasOfExperience());
                    present.setCreatedRoles(roles);
                    present.setCreatedBy(username);
                    present.setCreateDate(LocalDate.now());
                    return doctorRepository.save(present);
                }).orElseThrow(()->new UsernameNotFoundException("DOCTER NOT FOUND WITH FOLLOWING ID : "+id));
    }

}

package com.HospitalManagement.AppointmentService.controller;

import com.HospitalManagement.AppointmentService.entities.Appointment;
import com.HospitalManagement.AppointmentService.repository.AppointmentRepository;
import com.HospitalManagement.AppointmentService.service.AppointmentService;
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {
    private final AppointmentService appointmentService;
    private final AppointmentRepository appointmentRepository;
    public AppointmentController(AppointmentService appointmentService,AppointmentRepository appointmentRepository){
        this.appointmentService = appointmentService;
        this.appointmentRepository = appointmentRepository;
    }

    @PostMapping("/create")
    public ResponseEntity<?> creteAppointment(@RequestBody Appointment appointment,
                                              @RequestHeader (value = "X-Roles",required = false) String roles,
                                              @RequestHeader(value = "X-User",required = false) String username
                                              ){
        appointment.setCreatedBy(username);
        appointment.setCreatedRoles(roles);
        Appointment saved = appointmentService.create(appointment);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable int id) {
        return ResponseEntity.of(appointmentRepository.findById(id));
    }

}

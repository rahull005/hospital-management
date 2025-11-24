package com.HospitalManagement.NotificationService.controller;

import com.HospitalManagement.NotificationService.model.NotificationAppointment;
import com.HospitalManagement.NotificationService.repository.NotificationRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    private final NotificationRepository repo;

    public NotificationController(NotificationRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/all")
    public List<NotificationAppointment> getAll() {
        return repo.findAll();
    }
}

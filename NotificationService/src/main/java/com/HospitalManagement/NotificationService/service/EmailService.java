package com.HospitalManagement.NotificationService.service;

import com.HospitalManagement.NotificationService.dtos.AppointmentEventDTO;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final JavaMailSender javaMailSender;
    public EmailService(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }

    public void sendAppointmentConfirmation(String email, AppointmentEventDTO event){
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);
        msg.setSubject("Appointment Confirmation - " + event.getAppointmentId());
        msg.setText("Your appointment is confirmed on:\n"
                + event.getAppointmentTime()
                + "\nPurpose: " + event.getPurpose());

        javaMailSender.send(msg);
    }

    public void sendAppointmentReminder(String email, AppointmentEventDTO event) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);
        msg.setSubject("Appointment Reminder!");
        msg.setText("Reminder: You have an appointment on:\n"
                + event.getAppointmentTime()
                + "\nPurpose: " + event.getPurpose());

        javaMailSender.send(msg);
    }
}

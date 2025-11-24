package com.hospitalManagement.AuthService.exceptions;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ErrorDetail {
    private LocalDateTime dateTime;
    private String message;

    public ErrorDetail(LocalDateTime dateTime, String message) {
        this.dateTime = dateTime;
        this.message = message;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

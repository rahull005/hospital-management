package com.hospitalManagement.AuthService.exceptions;

public class UserExists extends RuntimeException{
    public UserExists(String message) {
        super(message);
    }
}

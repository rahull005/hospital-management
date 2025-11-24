package com.hospitalManagement.AuthService.exceptionhandler;

import com.hospitalManagement.AuthService.exceptions.ErrorDetail;
import com.hospitalManagement.AuthService.exceptions.UserExists;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserExists.class)
    public ResponseEntity<?> userExistsException(UserExists userExists){
        ErrorDetail errorDetail = new ErrorDetail(LocalDateTime.now(),userExists.getMessage());
        return new ResponseEntity<>(errorDetail,HttpStatus.BAD_REQUEST);
    }

}

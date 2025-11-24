package com.hospitalManagement.AuthService.controller;

import com.hospitalManagement.AuthService.dtos.AuthRequest;
import com.hospitalManagement.AuthService.exceptions.UserExists;
import com.hospitalManagement.AuthService.jwt.JwtUtils;
import com.hospitalManagement.AuthService.model.User;
import com.hospitalManagement.AuthService.repository.UserRepo;
import com.hospitalManagement.AuthService.service.MyUserDetailsService;
import com.hospitalManagement.AuthService.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserRepo userRepo;

    @Autowired
    private UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    MyUserDetailsService myUserDetailsService;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user){
        if(userRepo.findByUsername(user.getUsername()).isPresent()){
            return ResponseEntity.badRequest().body("username name already exist !!!");
        }
        return new ResponseEntity<>(userService.saveUser(user),HttpStatus.CREATED);
    }

    @GetMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public String getUser(@RequestBody AuthRequest authRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword())
        );

        UserDetails userDetails = myUserDetailsService.loadUserByUsername(authRequest.getUsername());

        if(authentication.isAuthenticated()){
            return jwtUtils.generateToken(userDetails);
        }

        return "Token generation Failed";
    }
}

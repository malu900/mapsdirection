package com.location.maps.api.controller;

import com.location.maps.api.payload.request.LoginRequest;
import com.location.maps.api.payload.request.SignUpRequest;
import com.location.maps.api.payload.response.JwtAuthenticationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface AuthController {
    @PostMapping("/signin")
    JwtAuthenticationResponse AuthenticateUser(LoginRequest loginRequest);

    @PostMapping("/signup")
    ResponseEntity<?> registerUser(SignUpRequest signUpRequest);
}
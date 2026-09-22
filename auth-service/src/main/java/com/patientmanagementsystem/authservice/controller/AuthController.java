package com.patientmanagementsystem.authservice.controller;


import com.patientmanagementsystem.authservice.dto.LoginRequestDto;
import com.patientmanagementsystem.authservice.dto.LoginResponseDto;
import com.patientmanagementsystem.authservice.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "Generate token on our user login")
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody  LoginRequestDto loginRequestDto){
        Optional<String> tokenOpt = authService.login(loginRequestDto);

        if(tokenOpt.isEmpty()) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        String token = tokenOpt.get();
        return ResponseEntity.ok(new LoginResponseDto(token));

    }

    @Operation(summary = "Validate token")
    @GetMapping("/validate")
    public ResponseEntity<Void> validateToken(@RequestHeader("Authorization") String authHeader){
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return authService.validateToken(authHeader.substring(7))
                ? ResponseEntity.ok().build()
                :ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }


}

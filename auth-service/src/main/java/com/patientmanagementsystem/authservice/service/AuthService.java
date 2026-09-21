package com.patientmanagementsystem.authservice.service;


import com.patientmanagementsystem.authservice.dto.LoginRequestDto;
import com.patientmanagementsystem.authservice.model.User;
import com.patientmanagementsystem.authservice.util.JwtUtil;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public Optional<String> login(LoginRequestDto loginRequestDto){
        Optional<String> token = userService.findByEmail(loginRequestDto.getEmail())
                .filter(u -> passwordEncoder.matches(loginRequestDto.getPassword(),u.getPassword()))
                        .map(u->jwtUtil.generateToken(u.getEmail(),u.getRole()));
        return token;

    }

    public Boolean validateToken(String authHeader){
        try{
            jwtUtil.validateToken(authHeader);
            return true;
        }catch (JwtException ex){
            log.error("jwt error: {}", ex.getMessage());
            return false;
        }
    }

}

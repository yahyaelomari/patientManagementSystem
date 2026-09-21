package com.patientmanagementsystem.authservice.util;

import com.patientmanagementsystem.authservice.enums.Roles;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Slf4j
@Component
public class JwtUtil {

    private final Key secretKey;
    private int TOKEN_EXPIRATION_PERIOD= 1000* 3600* 24;

    public JwtUtil(@Value("${jwt.secret}") String secret){
        byte[] keyBytes = Base64.getDecoder().decode(secret.getBytes(StandardCharsets.UTF_8));

        this.secretKey = Keys.hmacShaKeyFor(keyBytes);

    }

    public String generateToken(String email, Roles role) {
        return Jwts.builder().subject(email)
                .claim("role",role)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+TOKEN_EXPIRATION_PERIOD))
                .signWith(secretKey)
                .compact();
    }

    public void validateToken(String token) {
        try{
            Jwts.parser().verifyWith((SecretKey) secretKey)
                    .build()
                    .parseSignedClaims(token);
        }
        catch(SignatureException ex){
            log.error("jwt error: {}", ex.getMessage());
            throw new JwtException("Invalid Jwt signature");
        }catch(JwtException ex){
            log.error("jwt error: {}", ex.getMessage());
            throw new JwtException("Invalid jwt");
        }

    }
}

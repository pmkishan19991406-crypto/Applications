package com.kishan.securevault.security;

import java.util.Base64;
import java.util.Date;
import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    @Value("${app.jwt.secret}")
    private String secret;

    @Value("${app.jwt.expiration}")
    private long expiration;

    // Create secret key
    private SecretKey getKey() {
        byte[] keyBytes = Base64.getDecoder().decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // Generate JWT token
    public String generateToken(String email) {

        Date currentTime = new Date();
        Date expiryTime =new Date(currentTime.getTime() + expiration);

        String token = Jwts.builder()
                .subject(email)
                .issuedAt(currentTime)
                .expiration(expiryTime)
                .signWith(getKey())
                .compact();

        return token;
    }

    // Get email from token
    public String extractEmail(String token) {
        Claims claims = getClaims(token);
        return claims.getSubject();
    }

    // Read token details
    private Claims getClaims(String token) {

        Claims claims = Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims;
    }

    // Check whether token is valid
    public boolean isValid(String token) {

        try {
            getClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
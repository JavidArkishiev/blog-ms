package com.example.blogms.service.impl;

import com.example.blogms.service.JWTService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JWTServiceImpl implements JWTService {
    @Value("${jwt.secret}")
    private String secretKey;

    private Key getSignKey() {
        byte[] key = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(key);
    }

    public Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractUsername(String token) {
        return extractAllClaims(token)
                .getSubject();
    }

    @Override
    public String extractFirstName(String token) {
        return extractAllClaims(token)
                .get("firstName", String.class);
    }

    @Override
    public String extractLastName(String token) {
        return extractAllClaims(token)
                .get("lastName", String.class);
    }

    public List<String> extractAuthorities(String token) {
        return extractAllClaims(token)
                .get("authorities", List.class);
    }

    public boolean isTokenValid(String token, String userEmail) {
        final String username = extractUsername(token);
        return username.equals(userEmail) && !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token) {
        return extractAllClaims(token)
                .getExpiration()
                .before(new Date());
    }

}

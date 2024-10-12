package com.example.blogms.service.impl;

import io.jsonwebtoken.Claims;

import java.util.List;

public interface JWTService {

    Claims extractAllClaims(String token);

    String extractUsername(String token);

    String extractFirstName(String token);

    String extractLastName(String token);

    List<String> extractAuthorities(String token);

    boolean isTokenValid(String token, String userEmail);

    boolean isTokenExpired(String token);

}


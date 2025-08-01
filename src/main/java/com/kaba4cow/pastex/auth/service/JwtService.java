package com.kaba4cow.pastex.auth.service;

import org.springframework.security.core.userdetails.UserDetails;

import com.kaba4cow.pastex.domain.user.model.User;

public interface JwtService {

	String generateToken(User user);

	boolean isTokenValid(String token, UserDetails userDetails);

	String extractUserId(String token);

}

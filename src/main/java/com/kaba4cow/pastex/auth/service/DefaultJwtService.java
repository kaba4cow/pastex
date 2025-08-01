package com.kaba4cow.pastex.auth.service;

import java.security.Key;
import java.util.Date;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.kaba4cow.pastex.auth.jwt.JwtProperties;
import com.kaba4cow.pastex.domain.user.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DefaultJwtService implements JwtService {

	private final JwtProperties jwtProperties;

	@Override
	public String generateToken(User user) {
		return Jwts.builder()//
				.setSubject(user.getId().toString())//
				.setIssuedAt(new Date())//
				.setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getExpirationMillis()))//
				.signWith(getSignInKey(), SignatureAlgorithm.HS256)//
				.compact();
	}

	private Key getSignInKey() {
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtProperties.getSecretKey()));
	}

	@Override
	public boolean isTokenValid(String token, UserDetails userDetails) {
		return extractUserId(token).equals(userDetails.getUsername()) && !isTokenExpired(token);
	}

	private boolean isTokenExpired(String token) {
		return extractAllClaims(token).getExpiration().before(new Date());
	}

	@Override
	public String extractUserId(String token) {
		return extractAllClaims(token).getSubject();
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder()//
				.setSigningKey(getSignInKey())//
				.build()//
				.parseClaimsJws(token)//
				.getBody();
	}

}

package com.kaba4cow.pastex.auth.jwt;

import java.io.IOException;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.kaba4cow.pastex.auth.service.JwtService;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private static final String AUTH_HEADER = "Authorization";

	private static final String BEARER = "Bearer ";

	private final JwtService jwtService;

	private final UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			if (isAuthRequest(request))
				processAuthRequest(request);
			filterChain.doFilter(request, response);
		} catch (ExpiredJwtException exception) {
			respondUnauthorized(response, "Token expired");
		} catch (JwtException exception) {
			respondUnauthorized(response, "Invalid token");
		}
	}

	public void respondUnauthorized(HttpServletResponse response, String message) throws IOException {
		if (Objects.nonNull(response)) {
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			response.setContentType("application/json");
			response.getWriter().write(String.format("{\"error\": \"%s\"}", message));
		}
	}

	private boolean isAuthRequest(HttpServletRequest request) {
		String authHeader = request.getHeader(AUTH_HEADER);
		return Objects.nonNull(authHeader) && authHeader.startsWith(BEARER);
	}

	private void processAuthRequest(HttpServletRequest request) {
		String jwt = extractToken(request);
		String userId = jwtService.extractUserId(jwt);
		if (shouldAuthenticate(userId))
			authenticate(jwt, userId, request);
	}

	private String extractToken(HttpServletRequest request) {
		return request.getHeader(AUTH_HEADER).substring(BEARER.length());
	}

	private boolean shouldAuthenticate(String userId) {
		return Objects.nonNull(userId) && Objects.isNull(SecurityContextHolder.getContext().getAuthentication());
	}

	private void authenticate(String jwt, String userId, HttpServletRequest request) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(userId);
		if (jwtService.isTokenValid(jwt, userDetails))
			createAuthentication(userDetails, request);
	}

	private void createAuthentication(UserDetails userDetails, HttpServletRequest request) {
		UsernamePasswordAuthenticationToken authToken = createAuthToken(userDetails);
		WebAuthenticationDetails authDetails = createAuthDetials(request);
		authToken.setDetails(authDetails);
		SecurityContextHolder.getContext().setAuthentication(authToken);
	}

	private UsernamePasswordAuthenticationToken createAuthToken(UserDetails userDetails) {
		return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	}

	private WebAuthenticationDetails createAuthDetials(HttpServletRequest request) {
		return new WebAuthenticationDetailsSource().buildDetails(request);
	}

}

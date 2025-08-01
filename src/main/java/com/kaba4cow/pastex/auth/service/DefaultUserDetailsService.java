package com.kaba4cow.pastex.auth.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kaba4cow.pastex.domain.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DefaultUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		return userRepository.findById(Long.valueOf(id)).orElseThrow(() -> new UsernameNotFoundException("User not found"));
	}

}

package com.kaba4cow.pastex.domain.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kaba4cow.pastex.common.exception.NotFoundException;
import com.kaba4cow.pastex.domain.user.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	default User findByIdOrThrow(Long id) {
		return findById(id).orElseThrow(() -> new NotFoundException("User", id));
	}

	default User findByEmailOrThrow(String email) {
		return findByEmail(email).orElseThrow(() -> new NotFoundException("User", email));
	}

	Optional<User> findByEmail(String email);

	boolean existsByEmail(String email);

}

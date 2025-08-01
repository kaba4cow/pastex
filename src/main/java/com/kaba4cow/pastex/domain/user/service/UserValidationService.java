package com.kaba4cow.pastex.domain.user.service;

public interface UserValidationService {

	void ensureEmailIsAvailable(String email);

	void ensurePasswordsMatch(String password, String passwordHash);

}

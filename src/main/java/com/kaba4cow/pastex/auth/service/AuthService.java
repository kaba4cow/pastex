package com.kaba4cow.pastex.auth.service;

import com.kaba4cow.pastex.auth.dto.AuthDto;
import com.kaba4cow.pastex.auth.dto.AuthRequest;
import com.kaba4cow.pastex.auth.dto.RegisterRequest;

public interface AuthService {

	void register(RegisterRequest request);

	AuthDto authenticate(AuthRequest request);

}

package com.kaba4cow.pastex.domain.paste.service;

import java.util.Objects;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kaba4cow.pastex.common.exception.PasteAccessException;
import com.kaba4cow.pastex.domain.paste.model.Paste;
import com.kaba4cow.pastex.domain.user.model.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DefaultPasteAccessService implements PasteAccessService {

	private final PasswordEncoder passwordEncoder;

	@Override
	public void requestAccess(Paste paste, String password, User requester) {
		if (!paste.isSecured() || paste.authorEquals(requester))
			return;
		if (Objects.isNull(password))
			throw new PasteAccessException("Paste is secured");
		else if (!passwordEncoder.matches(password, paste.getPasswordHash()))
			throw new PasteAccessException("Wrong paste password");
	}

}

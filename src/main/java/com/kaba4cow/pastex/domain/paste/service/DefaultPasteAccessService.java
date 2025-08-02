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
	public void checkAccess(Paste paste, String password, User requester) {
		if (!paste.isSecured() || isAuthor(paste, requester))
			return;
		if (Objects.isNull(password))
			throw new PasteAccessException("Paste is secured");
		else if (!passwordEncoder.matches(password, paste.getPasswordHash()))
			throw new PasteAccessException("Wrong paste password");
	}

	private boolean isAuthor(Paste paste, User user) {
		if (Objects.isNull(user))
			return false;
		User author = paste.getAuthor();
		if (Objects.isNull(author))
			return false;
		return Objects.equals(author.getId(), user.getId());
	}

}

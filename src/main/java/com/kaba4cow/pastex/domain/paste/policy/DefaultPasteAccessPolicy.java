package com.kaba4cow.pastex.domain.paste.policy;

import java.util.Objects;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.kaba4cow.pastex.common.exception.PasteAccessException;
import com.kaba4cow.pastex.domain.paste.model.Paste;
import com.kaba4cow.pastex.domain.user.model.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class DefaultPasteAccessPolicy implements PasteAccessPolicy {

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

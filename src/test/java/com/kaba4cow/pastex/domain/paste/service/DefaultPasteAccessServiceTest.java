package com.kaba4cow.pastex.domain.paste.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.kaba4cow.pastex.common.exception.PasteAccessException;
import com.kaba4cow.pastex.domain.paste.model.Paste;
import com.kaba4cow.pastex.domain.user.model.User;

@ExtendWith(MockitoExtension.class)
public class DefaultPasteAccessServiceTest {

	@Mock
	private PasswordEncoder passwordEncoder;

	@InjectMocks
	private DefaultPasteAccessService pasteAccessService;

	@Test
	public void requestAccess_notIsSecured_shouldDoNothing() {
		Paste paste = mock(Paste.class);
		when(paste.isSecured()).thenReturn(false);

		assertDoesNotThrow(() -> pasteAccessService.requestAccess(paste, null, null));
	}

	@Test
	public void requestAccess_isSecured_authorEquals_shouldDoNothing() {
		User user = mock(User.class);

		Paste paste = mock(Paste.class);
		when(paste.isSecured()).thenReturn(true);
		when(paste.authorEquals(user)).thenReturn(true);

		assertDoesNotThrow(() -> pasteAccessService.requestAccess(paste, null, user));
	}

	@Test
	public void requestAccess_isSecured_NotAuthorEquals_passwordEncoderMatches_shouldDoNothing() {
		User user = mock(User.class);

		Paste paste = mock(Paste.class);
		when(paste.isSecured()).thenReturn(true);
		when(paste.authorEquals(user)).thenReturn(false);
		when(paste.getPasswordHash()).thenReturn("password");

		when(passwordEncoder.matches(any(), any())).thenReturn(true);

		assertDoesNotThrow(() -> pasteAccessService.requestAccess(paste, "password", user));
	}

	@Test
	public void requestAccess_isSecured_NotAuthorEquals_passwordIsNull_shouldThrowPasteAccessException() {
		User user = mock(User.class);

		Paste paste = mock(Paste.class);
		when(paste.isSecured()).thenReturn(true);
		when(paste.authorEquals(user)).thenReturn(false);

		assertThrows(PasteAccessException.class, () -> pasteAccessService.requestAccess(paste, null, user));
	}

	@Test
	public void requestAccess_isSecured_NotAuthorEquals_notPasswordEncoderMatches_shouldThrowPasteAccessException() {
		User user = mock(User.class);

		Paste paste = mock(Paste.class);
		when(paste.isSecured()).thenReturn(true);
		when(paste.authorEquals(user)).thenReturn(false);
		when(paste.getPasswordHash()).thenReturn("password");

		when(passwordEncoder.matches(any(), any())).thenReturn(false);

		assertThrows(PasteAccessException.class, () -> pasteAccessService.requestAccess(paste, "wrong_password", user));
	}

}

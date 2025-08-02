package com.kaba4cow.pastex.domain.paste.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.kaba4cow.pastex.domain.user.model.User;

public class PasteTest {

	@Test
	public void isSecured_withPassword_shouldReturnTrue() {
		Paste paste = Paste.builder()//
				.passwordHash("password-hash")//
				.build();

		assertTrue(paste.isSecured());
	}

	@Test
	public void isSecured_noPassword_shouldReturnFalse() {
		Paste paste = Paste.builder()//
				.passwordHash(null)//
				.build();

		assertFalse(paste.isSecured());
	}

	@Test
	public void authorEquals_noAuthor_noUser_shouldReturnFalse() {
		Paste paste = Paste.builder()//
				.author(null)//
				.build();

		assertFalse(paste.authorEquals(null));
	}

	@Test
	public void authorEquals_withAuthor_noUser_shouldReturnFalse() {
		User author = User.builder()//
				.id(1L)//
				.build();

		Paste paste = Paste.builder()//
				.author(author)//
				.build();

		assertFalse(paste.authorEquals(null));
	}

	@Test
	public void authorEquals_withAuthor_withUser_differentIds_shouldReturnFalse() {
		User author = User.builder()//
				.id(1L)//
				.build();
		User user = User.builder()//
				.id(2L)//
				.build();

		Paste paste = Paste.builder()//
				.author(author)//
				.build();

		assertFalse(paste.authorEquals(user));
	}

	@Test
	public void authorEquals_withAuthor_withUser_sameIds_shouldReturnTrue() {
		User author = User.builder()//
				.id(1L)//
				.build();
		User user = User.builder()//
				.id(1L)//
				.build();

		Paste paste = Paste.builder()//
				.author(author)//
				.build();

		assertTrue(paste.authorEquals(user));
	}

}

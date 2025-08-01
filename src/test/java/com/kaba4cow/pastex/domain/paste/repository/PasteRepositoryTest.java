package com.kaba4cow.pastex.domain.paste.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import com.kaba4cow.pastex.common.exception.NotFoundException;
import com.kaba4cow.pastex.domain.paste.model.Paste;

@DataJpaTest
@Transactional
public class PasteRepositoryTest {

	@Autowired
	private PasteRepository pasteRepository;

	@Test
	public void saveAndFindById() {
		Paste paste = Paste.builder()//
				.content("test content")//
				.author(null)//
				.build();

		pasteRepository.save(paste);

		Paste found = pasteRepository.findById(paste.getId()).orElse(null);
		assertNotNull(found);
		assertEquals(paste.getContent(), found.getContent());
	}

	@Test
	public void findByIdOrThrow_returns() {
		Paste paste = Paste.builder()//
				.content("test content")//
				.author(null)//
				.build();

		pasteRepository.save(paste);

		Paste found = pasteRepository.findByIdOrThrow(paste.getId());
		assertNotNull(found);
		assertEquals(paste.getId(), found.getId());
	}

	@Test
	public void findByIdOrThrow_throwsNotFound() {
		assertThrows(NotFoundException.class, () -> pasteRepository.findByIdOrThrow(UUID.randomUUID()));
	}

}

package com.kaba4cow.pastex.domain.paste.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.kaba4cow.pastex.domain.paste.model.Paste;

@ExtendWith(MockitoExtension.class)
public class PasteMapperTest {

	@InjectMocks
	private PasteMapper pasteMapper;

	@Test
	public void test_mapToDto() {
		Paste paste = Paste.builder()//
				.id(UUID.randomUUID())//
				.content("content")//
				.createdAt(LocalDateTime.now())//
				.build();

		PasteDto dto = pasteMapper.mapToDto(paste);

		assertNotNull(dto);
		assertEquals(paste.getId(), dto.getId());
		assertEquals(paste.getContent(), dto.getContent());
		assertEquals(paste.getCreatedAt(), dto.getCreatedAt());
	}

}

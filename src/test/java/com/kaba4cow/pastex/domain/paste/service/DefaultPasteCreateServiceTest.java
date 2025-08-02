package com.kaba4cow.pastex.domain.paste.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.kaba4cow.pastex.domain.paste.dto.PasteCreateRequest;
import com.kaba4cow.pastex.domain.paste.dto.PasteDto;
import com.kaba4cow.pastex.domain.paste.dto.PasteMapper;
import com.kaba4cow.pastex.domain.paste.model.Paste;
import com.kaba4cow.pastex.domain.paste.repository.PasteRepository;

@ExtendWith(MockitoExtension.class)
public class DefaultPasteCreateServiceTest {

	@Mock
	private PasteRepository pasteRepository;

	@Mock
	private PasteMapper pasteMapper;

	@InjectMocks
	private DefaultPasteCreateService pasteCreateService;

	@Test
	public void createPaste_shouldSaveAndReturnDto() {
		String content = "test content";
		PasteCreateRequest request = PasteCreateRequest.builder()//
				.content(content)//
				.password(null)//
				.build();
		Paste savedPaste = Paste.builder()//
				.id(UUID.randomUUID())//
				.content(content)//
				.passwordHash(null)//
				.expiresAt(LocalDateTime.MAX)//
				.build();
		PasteDto expectedDto = PasteDto.builder()//
				.id(savedPaste.getId())//
				.content(content)//
				.expiresAt(LocalDateTime.MAX)//
				.build();

		when(pasteRepository.save(any(Paste.class))).thenReturn(savedPaste);
		when(pasteMapper.mapToDto(savedPaste)).thenReturn(expectedDto);

		PasteDto actualDto = pasteCreateService.createPaste(request, null);

		verify(pasteRepository).save(any(Paste.class));
		assertEquals(expectedDto, actualDto);
	}

}

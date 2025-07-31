package com.kaba4cow.pastex.domain.paste.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

import com.kaba4cow.pastex.common.exception.NotFoundException;
import com.kaba4cow.pastex.domain.paste.dto.PasteCreateRequest;
import com.kaba4cow.pastex.domain.paste.dto.PasteDto;
import com.kaba4cow.pastex.domain.paste.dto.PasteMapper;
import com.kaba4cow.pastex.domain.paste.model.Paste;
import com.kaba4cow.pastex.domain.paste.repository.PasteRepository;

@ExtendWith(MockitoExtension.class)
public class DefaultPasteServiceTest {

	@Mock
	private PasteRepository pasteRepository;

	@Mock
	private PasteMapper pasteMapper;

	@InjectMocks
	private DefaultPasteService pasteService;

	@Test
	public void createPaste_shouldSaveAndReturnDto() {
		String content = "test content";
		PasteCreateRequest request = PasteCreateRequest.builder()//
				.content(content)//
				.build();
		Paste savedPaste = Paste.builder()//
				.id(UUID.randomUUID())//
				.content(content)//
				.createdAt(LocalDateTime.now())//
				.build();
		PasteDto expectedDto = PasteDto.builder()//
				.id(savedPaste.getId())//
				.content(content)//
				.createdAt(savedPaste.getCreatedAt())//
				.build();

		when(pasteRepository.save(any(Paste.class))).thenReturn(savedPaste);
		when(pasteMapper.mapToDto(savedPaste)).thenReturn(expectedDto);

		PasteDto actualDto = pasteService.createPaste(request);

		verify(pasteRepository).save(any(Paste.class));
		assertEquals(expectedDto, actualDto);
	}

	@Test
	public void getPaste_shouldFindAndReturnDto() {
		String content = "test content";
		UUID id = UUID.randomUUID();

		Paste foundPaste = Paste.builder()//
				.id(id)//
				.content(content)//
				.createdAt(LocalDateTime.now())//
				.build();
		PasteDto expectedDto = PasteDto.builder()//
				.id(foundPaste.getId())//
				.content(foundPaste.getContent())//
				.createdAt(foundPaste.getCreatedAt())//
				.build();

		when(pasteRepository.findByIdOrThrow(id)).thenReturn(foundPaste);
		when(pasteMapper.mapToDto(foundPaste)).thenReturn(expectedDto);

		PasteDto actualDto = pasteService.getPaste(id);

		verify(pasteRepository).findByIdOrThrow(id);
		assertEquals(expectedDto, actualDto);
	}

	@Test
	public void getPaste_shouldThrowNotFound() {
		when(pasteRepository.findByIdOrThrow(any(UUID.class))).thenThrow(NotFoundException.class);

		assertThrows(NotFoundException.class, () -> pasteService.getPaste(UUID.randomUUID()));

		verify(pasteRepository).findByIdOrThrow(any(UUID.class));
	}

}

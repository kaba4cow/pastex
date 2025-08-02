package com.kaba4cow.pastex.domain.paste.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
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
import com.kaba4cow.pastex.domain.paste.dto.PasteDto;
import com.kaba4cow.pastex.domain.paste.dto.PasteMapper;
import com.kaba4cow.pastex.domain.paste.model.Paste;
import com.kaba4cow.pastex.domain.paste.repository.PasteRepository;

@ExtendWith(MockitoExtension.class)
public class DefaultPasteReadServiceTest {

	@Mock
	private PasteRepository pasteRepository;

	@Mock
	private PasteAccessService pasteAccessPolicy;

	@Mock
	private PasteMapper pasteMapper;

	@InjectMocks
	private DefaultPasteReadService pasteReadService;

	@Test
	public void getPaste_shouldFindAndReturnDto() {
		String content = "test content";
		UUID id = UUID.randomUUID();

		Paste foundPaste = Paste.builder()//
				.id(id)//
				.content(content)//
				.expiresAt(LocalDateTime.MAX)//
				.build();
		PasteDto expectedDto = PasteDto.builder()//
				.id(foundPaste.getId())//
				.content(foundPaste.getContent())//
				.expiresAt(LocalDateTime.MAX)//
				.build();

		doNothing().when(pasteAccessPolicy).checkAccess(any(Paste.class), any(), any());
		when(pasteRepository.findByIdOrThrow(id)).thenReturn(foundPaste);
		when(pasteMapper.mapToDto(foundPaste)).thenReturn(expectedDto);

		PasteDto actualDto = pasteReadService.getPaste(id, null, null);

		verify(pasteRepository).findByIdOrThrow(id);
		assertEquals(expectedDto, actualDto);
	}

	@Test
	public void getPaste_shouldThrowNotFound() {
		when(pasteRepository.findByIdOrThrow(any(UUID.class))).thenThrow(NotFoundException.class);

		assertThrows(NotFoundException.class, () -> pasteReadService.getPaste(UUID.randomUUID(), null, null));

		verify(pasteRepository).findByIdOrThrow(any(UUID.class));
	}

}

package com.kaba4cow.pastex.domain.paste.service;

import java.util.UUID;

import com.kaba4cow.pastex.domain.paste.dto.PasteCreateRequest;
import com.kaba4cow.pastex.domain.paste.dto.PasteDto;

public interface PasteService {

	PasteDto createPaste(PasteCreateRequest request);

	PasteDto getPaste(UUID id);

}

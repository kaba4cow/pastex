package com.kaba4cow.pastex.domain.paste.service;

import com.kaba4cow.pastex.domain.paste.dto.PasteCreateRequest;
import com.kaba4cow.pastex.domain.paste.dto.PasteDto;

public interface PasteService {

	PasteDto createPaste(PasteCreateRequest request);
	
}

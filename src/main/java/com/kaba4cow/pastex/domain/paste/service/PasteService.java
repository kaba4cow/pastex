package com.kaba4cow.pastex.domain.paste.service;

import java.util.UUID;

import com.kaba4cow.pastex.domain.paste.dto.PasteCreateRequest;
import com.kaba4cow.pastex.domain.paste.dto.PasteDto;
import com.kaba4cow.pastex.domain.user.model.User;

public interface PasteService {

	PasteDto createPaste(PasteCreateRequest request, User author);

	PasteDto getPaste(UUID id);

}

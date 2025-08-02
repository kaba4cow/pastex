package com.kaba4cow.pastex.domain.paste.service;

import com.kaba4cow.pastex.domain.paste.dto.PasteCreateRequest;
import com.kaba4cow.pastex.domain.paste.dto.PasteDto;
import com.kaba4cow.pastex.domain.user.model.User;

public interface CreatePasteService {

	PasteDto createPaste(PasteCreateRequest request, User author);

}

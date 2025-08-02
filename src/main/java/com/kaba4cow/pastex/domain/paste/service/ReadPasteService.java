package com.kaba4cow.pastex.domain.paste.service;

import java.util.UUID;

import com.kaba4cow.pastex.domain.paste.dto.PasteDto;
import com.kaba4cow.pastex.domain.user.model.User;

public interface ReadPasteService {

	PasteDto getPaste(UUID id, String password, User requester);

}

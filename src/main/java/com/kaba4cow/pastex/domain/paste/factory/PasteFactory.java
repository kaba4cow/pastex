package com.kaba4cow.pastex.domain.paste.factory;

import com.kaba4cow.pastex.domain.paste.dto.PasteCreateRequest;
import com.kaba4cow.pastex.domain.paste.model.Paste;
import com.kaba4cow.pastex.domain.user.model.User;

public interface PasteFactory {

	Paste createPaste(PasteCreateRequest request, User author);

}

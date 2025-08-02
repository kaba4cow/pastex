package com.kaba4cow.pastex.domain.paste.service;

import com.kaba4cow.pastex.domain.paste.model.Paste;
import com.kaba4cow.pastex.domain.user.model.User;

public interface PasteAccessService {

	void checkAccess(Paste paste, String password, User requester);

}

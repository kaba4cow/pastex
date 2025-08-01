package com.kaba4cow.pastex.domain.paste.policy;

import com.kaba4cow.pastex.domain.paste.model.Paste;
import com.kaba4cow.pastex.domain.user.model.User;

public interface PasteAccessPolicy {

	void checkAccess(Paste paste, String password, User requester);

}

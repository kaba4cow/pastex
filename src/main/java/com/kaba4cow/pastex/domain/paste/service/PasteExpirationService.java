package com.kaba4cow.pastex.domain.paste.service;

import java.time.LocalDateTime;

public interface PasteExpirationService {

	LocalDateTime computeExpiresAt(Long expiration);

}

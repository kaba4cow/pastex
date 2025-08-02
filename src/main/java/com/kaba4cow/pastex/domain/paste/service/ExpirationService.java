package com.kaba4cow.pastex.domain.paste.service;

import java.time.LocalDateTime;

public interface ExpirationService {

	LocalDateTime computeExpiresAt(Long expiration);

}

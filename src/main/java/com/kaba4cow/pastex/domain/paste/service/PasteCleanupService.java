package com.kaba4cow.pastex.domain.paste.service;

import java.time.LocalDateTime;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.kaba4cow.pastex.domain.paste.repository.PasteRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class PasteCleanupService {

	private final PasteRepository pasteRepository;

	@PostConstruct
	public void onStartup() {
		deleteExpiredPastes();
	}

	@Scheduled(cron = "0 0 * * * *")
	public void deleteExpiredPastes() {
		int totalDeleted = pasteRepository.deleteAllByExpiresAtBefore(LocalDateTime.now());
		log.info("Deleted {} expired pastes", totalDeleted);
	}

}

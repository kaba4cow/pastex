package com.kaba4cow.pastex.domain.paste;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kaba4cow.pastex.common.exception.NotFoundException;
import com.kaba4cow.pastex.domain.paste.model.Paste;

public interface PasteRepository extends JpaRepository<Paste, UUID> {

	default Paste findByIdOrThrow(UUID id) {
		return findById(id).orElseThrow(() -> new NotFoundException("Paste", id));
	}

}

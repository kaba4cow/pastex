package com.kaba4cow.pastex.domain.paste;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PasteRepository extends JpaRepository<Paste, UUID> {

}

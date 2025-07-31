package com.kaba4cow.pastex.pastes.domain.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kaba4cow.pastex.pastes.domain.model.Paste;

public interface PasteRepository extends JpaRepository<Paste, UUID> {

}

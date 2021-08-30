package com.root.app.DraftTicket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface DraftTicketRepository extends JpaRepository<DraftTicket, Long> {
    @Query("SELECT dt FROM DraftTicket dt WHERE dt.id = ?1")
    Optional<DraftTicket> findDraftTicketById(Long id);
}

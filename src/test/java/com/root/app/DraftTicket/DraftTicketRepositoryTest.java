package com.root.app.DraftTicket;

import com.root.app.Passenger.Passenger;
import com.root.app.Passenger.PassengerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class DraftTicketRepositoryTest {


    @Autowired
    private DraftTicketRepository underTest;


    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @DisplayName("Draft Ticket should be found by Id")
    @Test
    void findDraftTicketById() {
        Long id;
        DraftTicket draftTicket = new DraftTicket();
        id = draftTicket.getId();
        underTest.save(draftTicket);
        boolean expected = underTest.findDraftTicketById(id).isPresent();

        assertTrue(expected);
    }

    @DisplayName("Draft Ticket should not be found by Id")
    @Test
    void shouldNotFindPassengerById() {
        Long id = 666L;

        boolean expected = underTest.findDraftTicketById(id).isPresent();

        assertFalse(expected);
    }
}
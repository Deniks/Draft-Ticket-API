package com.root.app.DraftTicket;

import com.root.app.Passenger.Passenger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

class DraftTicketServiceTest {

    @Mock
    private DraftTicketRepository draftTicketRepository;
    private DraftTicketService underTest;

    @BeforeEach
    void setUp() {
        underTest = new DraftTicketService(draftTicketRepository);
    }

    @DisplayName("All draft tickets should be returned")
    @Test
    void canGetAllDraftTickets() {
        underTest.getDraftTickets();
        verify(draftTicketRepository).findAll();
    }

    @DisplayName("Draft ticket should be added")
    @Test
    void canAddDraftTicket() {
        // expected
        List<Passenger> passengers = new ArrayList<Passenger>();
        DraftTicketRequest draftTicketRequest = new DraftTicketRequest(passengers);
        underTest.addNewDraftTicket(draftTicketRequest);
        ArgumentCaptor<DraftTicket> draftTicketArgumentCaptor = ArgumentCaptor.forClass(DraftTicket.class);

        verify(draftTicketRepository).save(draftTicketArgumentCaptor.capture());
    }

    @DisplayName("Draft ticket should be deleted")
    @Test
    void canDeleteDraftTicket() {
        Long id = 30L;
        given(draftTicketRepository.existsById(id)).willReturn(true);
        verify(draftTicketRepository).deleteById(id);
    }

    @DisplayName("There should be thrown an error when deleting non-existing draft ticket")
    @Test
    void willThrowErrorWhenDeleteDraftTicketNotFound() {
        Long id = 3000L;
        given(draftTicketRepository.existsById(id)).willReturn(false);
        assertThrows(IllegalStateException.class, () -> underTest.deleteDraftTicket(id), "Draft ticket with Id - " + id + "does not exist");
        verify(draftTicketRepository, never()).deleteById(any());
    }

}
package com.root.app.DraftTicket;

import com.root.app.Passenger.Passenger;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/draft-tickets")
public class DraftTicketController {
    private final DraftTicketService draftTicketService;

    public DraftTicketController(DraftTicketService draftTicketService) {
        this.draftTicketService = draftTicketService;
    }

    @GetMapping
    public List<DraftTicket> getDraftTickets() {
        return draftTicketService.getDraftTickets();
    }

    @GetMapping(path = "{draftTicketId}")
    public Optional<DraftTicket> getDraftTicket(@PathVariable("draftTicketId") Long draftTicketId) {
        return draftTicketService.getDraftTicket(draftTicketId);
    }


    @PostMapping
    public void registerNewDraftTicket(@RequestBody DraftTicketRequest draftTicketRequest) {
        draftTicketService.addNewDraftTicket(draftTicketRequest);
    }

    @DeleteMapping(path = "{draftTicketId}")
    public void deleteDraftTicket(@PathVariable("draftTicketId") Long draftTicketId) {
        draftTicketService.deleteDraftTicket(draftTicketId);
    }
}

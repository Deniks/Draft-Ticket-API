package com.root.app.DraftTicket;

import com.root.app.Passenger.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Transient;
import java.util.List;
import java.util.Optional;

@Service
public class DraftTicketService {
    private final DraftTicketRepository draftTicketRepository;

    @Autowired
    public DraftTicketService(DraftTicketRepository draftTicketRepository) {
        this.draftTicketRepository = draftTicketRepository;
    }

    public List<DraftTicket> getDraftTickets() {
        return draftTicketRepository.findAll();
    }

    public Optional<DraftTicket> getDraftTicket(Long draftTicketId) {
        boolean exists =  draftTicketRepository.existsById(draftTicketId);
        if (!exists) {
            throw new IllegalStateException("Draft ticket with Id - " + draftTicketId + "does not exist");
        }
        return draftTicketRepository.findById(draftTicketId);
    }

    public void addNewDraftTicket(DraftTicketRequest draftTicketRequest) {
        System.out.println(draftTicketRequest);
        DraftTicket draftTicket = new DraftTicket();
        draftTicket.setPassengers(draftTicketRequest.getPassengers());
        draftTicket.calculateTotalPrice();
        System.out.println(draftTicket);
        draftTicketRepository.save(draftTicket);
    }

    public void deleteDraftTicket(Long draftTicketId) {
        boolean exists = draftTicketRepository.existsById(draftTicketId);
        if (!exists) {
            throw new IllegalStateException("Draft ticket with Id - " + draftTicketId + "does not exist");
        }
        draftTicketRepository.deleteById(draftTicketId);
    }

}

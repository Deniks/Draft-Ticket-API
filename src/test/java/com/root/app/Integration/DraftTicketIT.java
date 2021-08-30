package com.root.app.Integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.root.app.DraftTicket.DraftTicket;
import com.root.app.DraftTicket.DraftTicketRepository;
import com.root.app.DraftTicket.DraftTicketRequest;
import com.root.app.Passenger.Passenger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class DraftTicketIT {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private DraftTicketRepository draftTicketRepository;

    private final Faker faker = new Faker();

    @Test
    void canRegisterNewDraftTicket() throws Exception {
        List<Passenger> passengers = new ArrayList<Passenger>();
        DraftTicketRequest draftTicketRequest = new DraftTicketRequest(passengers);
        ResultActions resultActions = mockMvc.perform(post("/api/v1/draft-tickets").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(draftTicketRequest)));

        resultActions.andExpect(status().isOk());
    }

}

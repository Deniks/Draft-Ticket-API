package com.root.app.Integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.root.app.Passenger.Passenger;
import com.root.app.Passenger.PassengerRepository;
import com.root.app.Passenger.PassengerRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PassengerIT {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PassengerRepository passengerRepository;

    private final Faker faker = new Faker();

    @Test
    void canRegisterNewPassenger() throws Exception {
        final int luggageCount = 4;
        final boolean isAdult = false;
        PassengerRequest passengerRequest = new PassengerRequest(luggageCount, isAdult);
        Passenger passenger = new Passenger(luggageCount, isAdult);
        ResultActions resultActions = mockMvc.perform(post("/api/v1/passengers").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(passengerRequest)));

        resultActions.andExpect(status().isOk());
    }
}

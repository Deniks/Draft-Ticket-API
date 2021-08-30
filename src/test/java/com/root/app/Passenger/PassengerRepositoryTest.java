package com.root.app.Passenger;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PassengerRepositoryTest {

    @Autowired
    private PassengerRepository underTest;


    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @DisplayName("Passenger should be found by Id")
    @Test
    void findPassengerById() {
        assertEquals(5, 2+3);
        Long id = 3L;
        Passenger passenger = new Passenger();
        underTest.save(passenger);
        boolean expected = underTest.findPassengerById(id).isPresent();

        assertTrue(expected);
    }

    @DisplayName("Passenger should not be found by Id")
    @Test
    void shouldNotFindPassengerById() {
        Long id = 666L;
        Passenger passenger = new Passenger();
        underTest.save(passenger);

        boolean expected = underTest.findPassengerById(id).isPresent();

        assertFalse(expected);
    }
}
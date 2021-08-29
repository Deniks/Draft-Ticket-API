package com.root.app.Passenger;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PassengerServiceTest {

    @Mock
    private PassengerRepository passengerRepository;
    private PassengerService underTest;

    @BeforeEach
    void setUp() {
        underTest = new PassengerService(passengerRepository);
    }

    @AfterEach
    void tearDown() {
    }

    @DisplayName("Can get all passengers")
    @Test
    void getPassengers() {
        underTest.getPassengers();
        verify(passengerRepository).findAll();
    }

    @DisplayName("Can get single passenger")
    @Test
    void getPassenger() {
        Long id = 5L;
        underTest.getPassenger(id);
        verify(passengerRepository).getOne(id);
    }

    @DisplayName("Can add new passenger")
    @Test
    void addNewPassenger() {
        Passenger passenger = new Passenger();
        underTest.addNewPassenger(3, false);
        ArgumentCaptor<Passenger> passengerArgumentCaptor = ArgumentCaptor.forClass(Passenger.class);
        verify(passengerRepository).save(passengerArgumentCaptor.capture());
    }

    @DisplayName("Can delete single passenger")
    @Test
    void deletePassenger() {
        Long id = 30L;
        given(passengerRepository.existsById(id)).willReturn(true);
        verify(passengerRepository).deleteById(id);
    }

    @DisplayName("Will throw error when trying to delete non-existing passenger")
    @Test
    void canThrowErrorWhenPassengerNotFound() {
        Long id = 999L;
        given(passengerRepository.existsById(id)).willReturn(false);
        assertThrows(IllegalAccessException.class, () -> underTest.deletePassenger(id), "Passenger with Id" + id + "does not exist");
        verify(passengerRepository, never()).deleteById(any());
    }
}
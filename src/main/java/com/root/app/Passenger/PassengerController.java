package com.root.app.Passenger;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/passengers")
public class PassengerController {
    private final PassengerService passengerService;

    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @GetMapping
    public List<Passenger> getPassengers() {
        return passengerService.getPassengers();
    }

    @GetMapping(path = "{passengerId}")
    public Optional<Passenger> getPassenger(@PathVariable("passengerId") Long passengerId) {
        return passengerService.getPassenger(passengerId);
    }


    @PostMapping
    public void registerNewPassenger(@RequestBody PassengerRequest passengerRequest) {
        passengerService.addNewPassenger(passengerRequest);
    }

    @DeleteMapping(path = "{passengerId}")
    public void deletePassenger(@PathVariable("passengerId") Long passengerId) {
        passengerService.deletePassenger(passengerId);
    }
}

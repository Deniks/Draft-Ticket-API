package com.root.app.Passenger;

import com.root.app.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PassengerService {
    private final PassengerRepository passengerRepository;

    @Autowired
    public PassengerService(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    public List<Passenger> getPassengers() {
        return passengerRepository.findAll();
    }

    public Optional<Passenger> getPassenger(Long passengerId) {
        boolean exists = passengerRepository.existsById(passengerId);
        if (!exists) {
            throw new IllegalStateException("Passenger with id - " + passengerId + "does not exist");
        }
        return passengerRepository.findById(passengerId);
    }

    public void addNewPassenger(int luggageCount, boolean isAdult) {
        Passenger passenger = new Passenger(luggageCount, isAdult);
        passenger.calculateBill();
        passenger.calculateLuggageCost();
        passenger.calculateTicketPrice();
        passengerRepository.save(passenger);
    }


    public void deletePassenger(Long passengerId) {
        passengerRepository.deleteById(passengerId);
    }

    private double roundTo2Decimals(double x) {
        return (double) Math.round(x * 100) / 100;
    }

    private double calculateBill() {
        double VAT = Payment.BASE_PRICE * Payment.VAT;

        return roundTo2Decimals(Payment.BASE_PRICE + VAT);
    }

    private double calculateBill(boolean isAdult) {
        double price = isAdult ? Payment.BASE_PRICE : Payment.BASE_PRICE * Payment.CHILD_DISCOUNT;
        double VAT = price *Payment.VAT;

        return roundTo2Decimals(price + VAT);
    }

    private double calculateLuggageCost(int luggageCount) {
        double price = luggageCount * Payment.LUGGAGE_DISCOUNT * Payment.BASE_PRICE;
        double VAT = price * Payment.VAT;

        return roundTo2Decimals(price + VAT);
    }
}

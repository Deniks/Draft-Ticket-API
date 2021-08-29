package com.root.app.Passenger;

import com.root.app.Payment;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity(name = "Passenger")
@Table(name = "passenger")
public class Passenger {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    Long id;

    @Column(
            name = "luggage_cost",
            nullable = false
    )
    private double luggageCost = 0;
    @Column(
            name = "bill",
            nullable = false
    )
    private double bill;
    @Column(
            name = "ticket_price",
            nullable = false
    )
    private double ticketPrice;
    @Column(
            name = "is_adult",
            nullable = false
    )
    private boolean isAdult = true;
    @Column(
            name = "luggage_count",
            nullable = false
    )
    private int luggageCount = 0;

    //region Passengers with bags and who are not adults
    public Passenger(boolean IsAdult) {
        this.isAdult = IsAdult;
    }

    public Passenger(int LuggageCount) {
        this.luggageCount = LuggageCount;
    }

    public Passenger(int LuggageCount, boolean IsAdult) {
        this.luggageCount = LuggageCount;
        this.isAdult = IsAdult;
    }
//endregion

    private double roundTo2Decimals(double x) {
        return (double) Math.round(x * 100) / 100;
    }

    public void calculateBill() {
        double price = isAdult ? Payment.BASE_PRICE : Payment.BASE_PRICE * Payment.CHILD_DISCOUNT;
        double VAT = price * Payment.VAT;

        bill = roundTo2Decimals(price + VAT);
    }

    public void calculateLuggageCost() {
        double price = luggageCount * Payment.LUGGAGE_DISCOUNT * Payment.BASE_PRICE;
        double VAT = price * Payment.VAT;

        luggageCost = roundTo2Decimals(price + VAT);
    }

    public void calculateTicketPrice() {
        ticketPrice = bill + luggageCost;
    }
}

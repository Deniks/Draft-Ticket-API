package com.root.app.DraftTicket;

import com.root.app.Passenger.Passenger;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "DraftTicket")
@Table(name = "draft_ticket")
public class DraftTicket {
    @Id
    @SequenceGenerator(
            name = "draft_ticket_sequence",
            sequenceName = "draft_ticket_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "draft_ticket_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @OneToMany(targetEntity = Passenger.class, cascade = CascadeType.MERGE)
    @JoinColumn(name = "passenger_id", referencedColumnName = "id")
    private List<Passenger> passengers;

    @Column(
            name = "total_price",
            nullable = false
    )
    private double totalPrice = 0;

    public void calculateTotalPrice() {
        for (Passenger passenger : this.passengers
        ) {
            this.totalPrice += passenger.getTicketPrice();
        }
    }
}

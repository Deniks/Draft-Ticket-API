package com.root.app.DraftTicket;

import com.root.app.Passenger.Passenger;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Transient;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DraftTicketRequest {
    List<Passenger> passengers;
}

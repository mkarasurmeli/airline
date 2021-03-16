package com.finartz.airline.domain.ticket;

import com.finartz.airline.domain.base.BaseEntity;
import com.finartz.airline.domain.flight.Flight;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "ticket")
public class Ticket extends BaseEntity {

    @NotNull
    @Column(name = "ticket_number", nullable = false, unique = true)
    private String ticketNumber;

    @NotNull
    @Column(name = "credit_card_no", nullable = false)
    private String creditCardNo;

    @NotNull
    @Positive
    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "flight_id" , nullable = false)
    private Flight flight;
}

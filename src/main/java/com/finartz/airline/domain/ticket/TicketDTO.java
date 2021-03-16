package com.finartz.airline.domain.ticket;

import com.finartz.airline.domain.flight.FlightDTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Getter
@Setter
public class TicketDTO {

    private Long id;

    @NotNull
    private String ticketNumber;

    @NotNull
    private String creditCardNo;

    private BigDecimal price;

    @NotNull
    private FlightDTO flight;
}

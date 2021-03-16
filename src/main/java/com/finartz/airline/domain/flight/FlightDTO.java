package com.finartz.airline.domain.flight;

import com.finartz.airline.domain.airlinecompany.AirlineCompanyDTO;
import com.finartz.airline.domain.route.RouteDTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class FlightDTO {

    private Long id;

    @NotNull
    private String pnrNo;

    @NotNull
    private Date date;

    @Positive
    @NotNull
    private Integer seatCount;

    @Positive
    @NotNull
    private BigDecimal initialPrice;

    private BigDecimal currentPrice;

    @NotNull
    private RouteDTO route;

    @NotNull
    private AirlineCompanyDTO airlineCompany;

}

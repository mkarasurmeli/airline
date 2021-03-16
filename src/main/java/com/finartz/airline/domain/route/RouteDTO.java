package com.finartz.airline.domain.route;

import com.finartz.airline.domain.airport.AirportDTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RouteDTO {

    private Long id;

    @NotNull
    private AirportDTO departure;

    @NotNull
    private AirportDTO destination;

}

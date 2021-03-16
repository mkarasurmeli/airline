package com.finartz.airline.domain.flight;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FlightMapper {

    FlightMapper INSTANCE = Mappers.getMapper(FlightMapper.class);

    FlightDTO to(Flight flight);

    Flight from(FlightDTO flightDTO);

}

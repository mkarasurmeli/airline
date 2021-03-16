package com.finartz.airline.domain.airport;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AirportMapper {

    AirportMapper INSTANCE = Mappers.getMapper(AirportMapper.class);

    AirportDTO to(Airport airport);

    Airport from(AirportDTO airportDTO);

}

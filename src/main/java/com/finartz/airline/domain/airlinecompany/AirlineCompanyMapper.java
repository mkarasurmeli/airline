package com.finartz.airline.domain.airlinecompany;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AirlineCompanyMapper {

    AirlineCompanyMapper INSTANCE = Mappers.getMapper(AirlineCompanyMapper.class);

    AirlineCompanyDTO to(AirlineCompany airlineCompany);

    AirlineCompany from(AirlineCompanyDTO airlineCompanyDTO);

}

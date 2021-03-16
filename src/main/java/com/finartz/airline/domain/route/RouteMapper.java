package com.finartz.airline.domain.route;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RouteMapper {

    RouteMapper INSTANCE = Mappers.getMapper(RouteMapper.class);

    RouteDTO to(Route route);

    Route from(RouteDTO routeDTO);

}

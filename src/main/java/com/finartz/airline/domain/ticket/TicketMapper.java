package com.finartz.airline.domain.ticket;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TicketMapper {

    TicketMapper INSTANCE = Mappers.getMapper(TicketMapper.class);

    TicketDTO to(Ticket ticket);

    Ticket from(TicketDTO ticketDTO);

}

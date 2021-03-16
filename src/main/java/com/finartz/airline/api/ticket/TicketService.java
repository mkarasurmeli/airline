package com.finartz.airline.api.ticket;

import com.finartz.airline.api.base.BaseService;
import com.finartz.airline.api.flight.FlightService;
import com.finartz.airline.domain.flight.Flight;
import com.finartz.airline.domain.ticket.Ticket;
import com.finartz.airline.domain.ticket.TicketRepository;
import com.finartz.airline.rest.ResponseCode;
import com.finartz.airline.rest.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TicketService implements BaseService<Ticket> {

    private final TicketRepository ticketRepository;
    private final FlightService flightService;

    public Optional<Ticket> findByTicketNumber(String ticketNumber) {
        return ticketRepository.findByTicketNumber(ticketNumber);
    }

    public void CancelTicketByTicketNumber(String ticketNumber) {
        Ticket ticket = findByTicketNumber(ticketNumber).orElseThrow(()-> new ApiException(ResponseCode.DATA_NOT_FOUND));
        ticketRepository.delete(ticket);
    }

    @Override
    public Page<Ticket> findAll(Pageable pageable) {
        return ticketRepository.findAll(pageable);
    }

    @Override
    public Optional<Ticket> findById(Long id) {
        return ticketRepository.findById(id);
    }

    @Override
    public Ticket save(Ticket ticket) {
        Flight flight = flightService.findById(ticket.getFlight().getId()).orElseThrow(() -> new ApiException(ResponseCode.DATA_NOT_FOUND));
        ticket.setPrice(flight.getCurrentPrice());
        ticket.setCreditCardNo(ticket.getCreditCardNo().replaceAll("\\D", "").replaceAll("(?<=.{6}).(?=.{4})", "*"));
        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket update(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public void delete(Long id) {
        Ticket ticket = findById(id).orElseThrow(()-> new ApiException(ResponseCode.DATA_NOT_FOUND));
        ticketRepository.delete(ticket);
    }
}

package com.finartz.airline.api.flight;

import com.finartz.airline.api.base.BaseService;
import com.finartz.airline.domain.flight.Flight;
import com.finartz.airline.domain.flight.FlightData;
import com.finartz.airline.domain.flight.FlightRepository;
import com.finartz.airline.rest.ResponseCode;
import com.finartz.airline.rest.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FlightService implements BaseService<Flight> {

    private final FlightRepository flightRepository;

    @Override
    public Page<Flight> findAll(Pageable pageable) {
        return flightRepository.findAll(pageable);
    }

    @Override
    public Optional<Flight> findById(Long id) {
        return flightRepository.findById(id);
    }

    @Override
    public Flight save(Flight flight) {
        return flightRepository.save(flight);
    }

    @Override
    public Flight update(Flight flight) {
        return flightRepository.save(flight);
    }

    @Override
    public void delete(Long id) {
        Flight flight = findById(id).orElseThrow(()-> new ApiException(ResponseCode.DATA_NOT_FOUND));
        flightRepository.delete(flight);
    }

    public Page<FlightData> findBySearchFilter(String pnrNo, String departureName, String destinationName, String airlineCompanyName, Pageable pageable) {
        return flightRepository.findBySearchFilter(pnrNo,departureName,destinationName,airlineCompanyName,pageable);
    }
}

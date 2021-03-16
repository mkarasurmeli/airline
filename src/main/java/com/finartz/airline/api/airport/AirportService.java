package com.finartz.airline.api.airport;

import com.finartz.airline.api.base.BaseService;
import com.finartz.airline.domain.airport.Airport;
import com.finartz.airline.domain.airport.AirportRepository;
import com.finartz.airline.rest.ResponseCode;
import com.finartz.airline.rest.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AirportService implements BaseService<Airport> {

    private final AirportRepository airportRepository;

    public Page<Airport> findAllByCity(String city,Pageable pageable) {
        return airportRepository.findAllByCity(city,pageable);
    }

    @Override
    public Page<Airport> findAll(Pageable pageable) {
        return airportRepository.findAll(pageable);
    }

    @Override
    public Optional<Airport> findById(Long id) {
        return airportRepository.findById(id);
    }

    @Override
    public Airport save(Airport airport) {
        return airportRepository.save(airport);
    }

    @Override
    public Airport update(Airport airport) {
        return airportRepository.save(airport);
    }

    @Override
    public void delete(Long id) {
        Airport airport = findById(id).orElseThrow(()-> new ApiException(ResponseCode.DATA_NOT_FOUND));
        airportRepository.delete(airport);
    }
}

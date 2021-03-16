package com.finartz.airline.api.route;

import com.finartz.airline.api.base.BaseService;
import com.finartz.airline.domain.route.Route;
import com.finartz.airline.domain.route.RouteRepository;
import com.finartz.airline.rest.ResponseCode;
import com.finartz.airline.rest.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RouteService implements BaseService<Route> {

    private final RouteRepository routeRepository;

    public Page<Route> findAllByDepartureName(String departureName, Pageable pageable) {

        return routeRepository.findAllByDepartureName(departureName, pageable);
    }

    public Page<Route> findAllByDestinationName(String destinationName, Pageable pageable) {
        return routeRepository.findAllByDestinationName(destinationName, pageable);
    }

    @Override
    public Page<Route> findAll(Pageable pageable) {
        return routeRepository.findAll(pageable);
    }

    @Override
    public Optional<Route> findById(Long id) {
        return routeRepository.findById(id);
    }

    @Override
    public Route save(Route route) {
        return routeRepository.save(route);
    }

    @Override
    public Route update(Route route) {
        return routeRepository.save(route);
    }

    @Override
    public void delete(Long id) {
        Route route = findById(id).orElseThrow(()-> new ApiException(ResponseCode.DATA_NOT_FOUND));
        routeRepository.delete(route);
    }

}

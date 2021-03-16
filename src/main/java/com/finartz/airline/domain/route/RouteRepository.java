package com.finartz.airline.domain.route;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {

    Page<Route> findAllByDepartureName(String departureName, Pageable pageable);

    Page<Route> findAllByDestinationName(String destinationName, Pageable pageable);
}

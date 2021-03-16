package com.finartz.airline.domain.flight;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    @Query("select " +
            "   flight.pnrNo as pnrNo, " +
            "   flight.date  as date, " +
            "   route.departure.name as departureName, " +
            "   route.destination.name as destinationName, " +
            "   airlineCompany.name as airlineCompanyName " +
            "from Flight flight " +
            "   join flight.route route " +
            "   join flight.airlineCompany airlineCompany " +
            "where " +
            "(:pnrNo is null or flight.pnrNo = :pnrNo) AND " +
            "(:departureName is null or route.departure.name like concat(:departureName,'%')) AND " +
            "(:destinationName is null or route.destination.name like concat(:destinationName,'%')) AND " +
            "(:airlineCompanyName is null or airlineCompany.name like concat(:airlineCompanyName,'%')) "
    )
    Page<FlightData> findBySearchFilter(String pnrNo, String departureName,
                                    String destinationName, String airlineCompanyName,
                                    Pageable pageable);
}

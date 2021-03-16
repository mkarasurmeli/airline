package com.finartz.airline.domain.flight;

import java.util.Date;

public interface FlightData {

    String getPnrNo();

    Date getDate();

    String getDepartureName();

    String getDestinationName();

    String getAirlineCompanyName();
    
}

package com.finartz.airline.domain.route;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.finartz.airline.domain.airport.Airport;
import com.finartz.airline.domain.base.BaseEntity;
import com.finartz.airline.domain.flight.Flight;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "route")
public class Route extends BaseEntity {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "departure_id" , nullable = false)
    private Airport departure;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "destination_id" , nullable = false)
    private Airport destination;

    @JsonIgnore
    @OneToMany(mappedBy = "route")
    private Set<Flight> flights;
}

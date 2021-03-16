package com.finartz.airline.domain.flight;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.finartz.airline.domain.airlinecompany.AirlineCompany;
import com.finartz.airline.domain.base.BaseEntity;
import com.finartz.airline.domain.route.Route;
import com.finartz.airline.domain.ticket.Ticket;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "flight")
public class Flight extends BaseEntity {

    @NotNull
    @Column(name = "pnr_no",nullable = false, unique = true)
    private String pnrNo;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date",nullable = false)
    private Date date;

    @Positive
    @NotNull
    @Column(name = "seat_count",nullable = false)
    private Integer seatCount;

    @Positive
    @NotNull
    @Column(name = "initial_price",nullable = false)
    private BigDecimal initialPrice;

    @Transient
    private BigDecimal currentPrice;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "route_id", nullable = false)
    private Route route;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "airline_company_id", nullable = false)
    private AirlineCompany airlineCompany;

    @JsonIgnore
    @OneToMany(mappedBy = "flight")
    private Set<Ticket> tickets = new HashSet<>();

    public BigDecimal getCurrentPrice() {
        int reservedSeatCount = tickets.size();
        int reservedSeatPersentage = (reservedSeatCount*100)/seatCount;
        double priceIncreasePersentage = (((reservedSeatPersentage / 10) * 10) / 100d) + 1;
        return initialPrice.multiply(BigDecimal.valueOf(priceIncreasePersentage));
    }
}

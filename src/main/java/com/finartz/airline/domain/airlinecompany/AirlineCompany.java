package com.finartz.airline.domain.airlinecompany;

import com.finartz.airline.domain.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Entity
@Table(name = "airline_company")
public class AirlineCompany extends BaseEntity {

    @NotBlank
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank
    @Column(name = "code", nullable = false)
    private String code;

    @NotBlank
    @Column(name = "identity_number", nullable = false)
    private String identityNumber;
}

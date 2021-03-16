package com.finartz.airline.domain.airlinecompany;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class AirlineCompanyDTO {

    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String code;

    @NotBlank
    private String identityNumber;
}

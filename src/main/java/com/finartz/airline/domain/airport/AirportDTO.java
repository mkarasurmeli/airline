package com.finartz.airline.domain.airport;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class AirportDTO {

    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String code;

    @NotBlank
    private String city;

}

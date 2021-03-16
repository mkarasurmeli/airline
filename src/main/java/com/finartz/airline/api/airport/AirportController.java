package com.finartz.airline.api.airport;

import com.finartz.airline.domain.airport.Airport;
import com.finartz.airline.domain.airport.AirportDTO;
import com.finartz.airline.domain.airport.AirportMapper;
import com.finartz.airline.rest.ResponseCode;
import com.finartz.airline.rest.exception.ApiException;
import com.finartz.airline.rest.response.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/airport")
@Api(description = "Havaalanı ile ilgili kaydetme,güncelleme ve silme işlemleri yapılır.")
public class AirportController {

    private final AirportService airportService;

    @GetMapping
    @ApiOperation("Verilen parametrelere göre havaalanı kayıtlarını döndürür.")
    public ApiResponse<Page<AirportDTO>> getAirportList(Pageable pageable) {

        Page<Airport> airportPage = airportService.findAll(pageable);
        List<AirportDTO> airportDTOList =
                airportPage
                        .stream()
                        .map(AirportMapper.INSTANCE::to)
                        .collect(Collectors.toList());

        return ApiResponse.of(new PageImpl<>(airportDTOList, pageable, airportPage.getTotalElements()));
    }

    @GetMapping("/city/{city}")
    @ApiOperation("Verilen parametrelere göre havaalanı kayıtlarını döndürür.")
    public ApiResponse<Page<AirportDTO>> getAirportListByCity(String city, Pageable pageable) {

        Page<Airport> airportPage = airportService.findAllByCity(city, pageable);
        List<AirportDTO> airportDTOList =
                airportPage
                        .stream()
                        .map(AirportMapper.INSTANCE::to)
                        .collect(Collectors.toList());

        return ApiResponse.of(new PageImpl<>(airportDTOList, pageable, airportPage.getTotalElements()));
    }


    @GetMapping("/{id}")
    @ApiOperation("Verilen id'ye göre havaalanı kaydını döndürür.")
    public ApiResponse<AirportDTO> getAirport(@PathVariable Long id) {
        Airport airport = airportService.findById(id).orElseThrow(() -> new ApiException(ResponseCode.DATA_NOT_FOUND));
        return ApiResponse.of(AirportMapper.INSTANCE.to(airport));
    }


    @PostMapping
    @ApiOperation("Yeni havaalanı kaydı oluşturur.")
    public ApiResponse<AirportDTO> save(@Valid @RequestBody AirportDTO airportDTO) {
        Airport airport = AirportMapper.INSTANCE.from(airportDTO);
        airport = airportService.save(airport);
        return ApiResponse.of(AirportMapper.INSTANCE.to(airport), ResponseCode.DATA_SAVE_SUCCESS);
    }

    @PutMapping
    @ApiOperation("Mevcut havaalanı kaydını günceller.")
    public ApiResponse<AirportDTO> update(@Valid @RequestBody AirportDTO airportDTO) {
        Airport airport = AirportMapper.INSTANCE.from(airportDTO);
        airport = airportService.update(airport);
        return ApiResponse.of(AirportMapper.INSTANCE.to(airport), ResponseCode.DATA_UPDATE_SUCCESS);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Id bilgisi verilen havaalanı kaydını siler.")
    public ApiResponse delete(@PathVariable Long id) {
        airportService.delete(id);
        return ApiResponse.success(ResponseCode.DATA_DELETE_SUCCESS);
    }
}

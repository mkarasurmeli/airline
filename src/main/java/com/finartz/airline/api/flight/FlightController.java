package com.finartz.airline.api.flight;

import com.finartz.airline.domain.flight.Flight;
import com.finartz.airline.domain.flight.FlightDTO;
import com.finartz.airline.domain.flight.FlightData;
import com.finartz.airline.domain.flight.FlightMapper;
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
@RequestMapping("/flight")
@Api(description = "Yapılacak uçuş ile ilgili kaydetme,güncelleme ve silme işlemleri yapılır.")
public class FlightController {

    private final FlightService flightService;

    @GetMapping("/search")
    @ApiOperation("Verilen parametrelere göre yapılacak uçuş kayıtlarını döndürür.")
    public ApiResponse<Page<FlightData>> getFlightList(
            @RequestParam(required = false) String pnrNo,
            @RequestParam(required = false) String departureName,
            @RequestParam(required = false) String destinationName,
            @RequestParam(required = false) String airlineCompanyName,
            Pageable pageable) {

        Page<FlightData> flightPage = flightService.findBySearchFilter(pnrNo, departureName, destinationName, airlineCompanyName, pageable);

        return ApiResponse.of(flightPage);
    }

    @GetMapping
    @ApiOperation("Tüm Uçuş kayıtlarını sayfalı olarak döndürür.")
    public ApiResponse<Page<FlightDTO>> getFlightList(Pageable pageable) {

        Page<Flight> flightPage = flightService.findAll(pageable);
        List<FlightDTO> flightDTOList =
                flightPage
                        .stream()
                        .map(FlightMapper.INSTANCE::to)
                        .collect(Collectors.toList());

        return ApiResponse.of(new PageImpl<>(flightDTOList, pageable, flightPage.getTotalElements()));
    }


    @GetMapping("/{id}")
    @ApiOperation("Verilen id'ye göre yapılacak uçuş kaydını döndürür.")
    public ApiResponse<FlightDTO> getFlight(@PathVariable Long id) {
        Flight flight = flightService.findById(id).orElseThrow(() -> new ApiException(ResponseCode.DATA_NOT_FOUND));
        return ApiResponse.of(FlightMapper.INSTANCE.to(flight));
    }


    @PostMapping
    @ApiOperation("Yeni yapılacak uçuş kaydı oluşturur.")
    public ApiResponse<FlightDTO> save(@Valid @RequestBody FlightDTO flightDTO) {
        Flight flight = FlightMapper.INSTANCE.from(flightDTO);
        flight = flightService.save(flight);
        return ApiResponse.of(FlightMapper.INSTANCE.to(flight), ResponseCode.DATA_SAVE_SUCCESS);
    }

    @PutMapping
    @ApiOperation("Mevcut yapılacak uçuş kaydını günceller.")
    public ApiResponse<FlightDTO> update(@Valid @RequestBody FlightDTO flightDTO) {
        Flight flight = FlightMapper.INSTANCE.from(flightDTO);
        flight = flightService.update(flight);
        return ApiResponse.of(FlightMapper.INSTANCE.to(flight), ResponseCode.DATA_UPDATE_SUCCESS);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Id bilgisi verilen yapılacak uçuş kaydını siler.")
    public ApiResponse delete(@PathVariable Long id) {
        flightService.delete(id);
        return ApiResponse.success(ResponseCode.DATA_DELETE_SUCCESS);
    }
}

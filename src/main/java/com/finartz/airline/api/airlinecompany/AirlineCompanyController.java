package com.finartz.airline.api.airlinecompany;

import com.finartz.airline.domain.airlinecompany.AirlineCompany;
import com.finartz.airline.domain.airlinecompany.AirlineCompanyDTO;
import com.finartz.airline.domain.airlinecompany.AirlineCompanyMapper;
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
@RequestMapping("/airline-company")
@Api(description = "Havayolu şirketi ile ilgili kaydetme,güncelleme ve silme işlemleri yapılır.")
public class AirlineCompanyController {

    private final AirlineCompanyService airlineCompanyService;

    @GetMapping
    @ApiOperation("Verilen parametrelere göre havayolu şirketi kayıtlarını döndürür.")
    public ApiResponse<Page<AirlineCompanyDTO>> getAirlineCompanyList(@RequestParam(value = "q", required = false) String searchText,
                                                                      Pageable pageable) {

        Page<AirlineCompany> airlineCompanyPage = airlineCompanyService.findBySearchText(searchText,pageable);
        List<AirlineCompanyDTO> airlineCompanyDTOList =
                airlineCompanyPage
                        .stream()
                        .map(AirlineCompanyMapper.INSTANCE::to)
                        .collect(Collectors.toList());

        return ApiResponse.of(new PageImpl<>(airlineCompanyDTOList, pageable, airlineCompanyPage.getTotalElements()));
    }


    @GetMapping("/{id}")
    @ApiOperation("Verilen id'ye göre havayolu şirketi kaydını döndürür.")
    public ApiResponse<AirlineCompanyDTO> getAirlineCompany(@PathVariable Long id) {
        AirlineCompany airlineCompany = airlineCompanyService.findById(id).orElseThrow(() -> new ApiException(ResponseCode.DATA_NOT_FOUND));
        return ApiResponse.of(AirlineCompanyMapper.INSTANCE.to(airlineCompany));
    }


    @PostMapping
    @ApiOperation("Yeni havayolu şirketi kaydı oluşturur.")
    public ApiResponse<AirlineCompanyDTO> save(@Valid @RequestBody AirlineCompanyDTO airlineCompanyDTO) {
        AirlineCompany airlineCompany = AirlineCompanyMapper.INSTANCE.from(airlineCompanyDTO);
        airlineCompany = airlineCompanyService.save(airlineCompany);
        return ApiResponse.of(AirlineCompanyMapper.INSTANCE.to(airlineCompany), ResponseCode.DATA_SAVE_SUCCESS);
    }

    @PutMapping
    @ApiOperation("Mevcut havayolu şirketi kaydını günceller.")
    public ApiResponse<AirlineCompanyDTO> update(@Valid @RequestBody AirlineCompanyDTO airlineCompanyDTO) {
        AirlineCompany airlineCompany = AirlineCompanyMapper.INSTANCE.from(airlineCompanyDTO);
        airlineCompany = airlineCompanyService.update(airlineCompany);
        return ApiResponse.of(AirlineCompanyMapper.INSTANCE.to(airlineCompany), ResponseCode.DATA_UPDATE_SUCCESS);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Id bilgisi verilen Havayolu şirketi kaydını siler.")
    public ApiResponse delete(@PathVariable Long id) {
        airlineCompanyService.delete(id);
        return ApiResponse.success(ResponseCode.DATA_DELETE_SUCCESS);
    }
}

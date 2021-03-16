package com.finartz.airline.api.airlinecompany;

import com.finartz.airline.api.base.BaseService;
import com.finartz.airline.domain.airlinecompany.AirlineCompany;
import com.finartz.airline.domain.airlinecompany.AirlineCompanyRepository;
import com.finartz.airline.rest.ResponseCode;
import com.finartz.airline.rest.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AirlineCompanyService implements BaseService<AirlineCompany> {

    private final AirlineCompanyRepository airlineCompanyRepository;

    public Page<AirlineCompany> findBySearchText(String searchText, Pageable pageable) {
        return airlineCompanyRepository.findByNameContainingOrCodeContaining(searchText, searchText, pageable);
    }

    @Override
    public Page<AirlineCompany> findAll(Pageable pageable) {
        return airlineCompanyRepository.findAll(pageable);
    }

    @Override
    public Optional<AirlineCompany> findById(Long id) {
        return airlineCompanyRepository.findById(id);
    }

    @Override
    public AirlineCompany save(AirlineCompany airlineCompany) {
        return airlineCompanyRepository.save(airlineCompany);
    }

    @Override
    public AirlineCompany update(AirlineCompany airlineCompany) {
        return airlineCompanyRepository.save(airlineCompany);
    }

    @Override
    public void delete(Long id) {
        AirlineCompany airlineCompany = findById(id).orElseThrow(()-> new ApiException(ResponseCode.DATA_NOT_FOUND));
        airlineCompanyRepository.delete(airlineCompany);
    }
}

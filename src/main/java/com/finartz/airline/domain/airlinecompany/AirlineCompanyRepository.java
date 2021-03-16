package com.finartz.airline.domain.airlinecompany;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirlineCompanyRepository extends JpaRepository<AirlineCompany, Long> {

    Page<AirlineCompany> findByNameContainingOrCodeContaining(String name, String code, Pageable pageable);
}

package com.finartz.airline.api.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BaseService<T> {

    Page<T> findAll(Pageable pageable);

    Optional<T> findById(Long id);

    T save(T entity);

    T update(T entity);

    void delete(Long id);
}

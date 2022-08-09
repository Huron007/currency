package com.kodilla.currency.repository;

import com.kodilla.currency.entity.Alert;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface AlertRepository extends CrudRepository<Alert, Long> {

    @Override
    List<Alert> findAll();

    @Override
    Optional<Alert> findById(Long id);

    @Override
    Alert save(Alert alert);

    @Override
    void deleteById(Long id);
}

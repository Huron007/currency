package com.kodilla.currency.repository;

import com.kodilla.currency.entity.Code;
import com.kodilla.currency.entity.Currency;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface CurrencyRepository extends CrudRepository<Currency, Long> {

    @Override
    List<Currency> findAll();

    @Override
    Optional<Currency> findById(Long id);

    @Override
    Currency save(Currency currency);

    @Override
    void deleteById(Long id);

    @Query
    List<Currency> checkDuplicates(@Param("CODE") Code code, @Param("DATE") LocalDate date);

    List<Currency> findByCode(Code code);

    Optional<Currency> findByEffectiveDate(LocalDate date);
}

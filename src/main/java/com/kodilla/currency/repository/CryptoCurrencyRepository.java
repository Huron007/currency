package com.kodilla.currency.repository;

import com.kodilla.currency.entity.Code;
import com.kodilla.currency.entity.CryptoCurrency;
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
public interface CryptoCurrencyRepository extends CrudRepository<CryptoCurrency, Long> {

    @Override
    List<CryptoCurrency> findAll();

    @Override
    Optional<CryptoCurrency> findById(Long id);

    @Override
    CryptoCurrency save(CryptoCurrency currency);

    @Override
    void deleteById(Long id);

    @Query
    List<CryptoCurrency> checkDuplicates(@Param("NAME") String name, @Param("DATE") LocalDate date);

    List<CryptoCurrency> findByCode(Code code);

    List<CryptoCurrency> findByEffectiveDate(LocalDate date);
}

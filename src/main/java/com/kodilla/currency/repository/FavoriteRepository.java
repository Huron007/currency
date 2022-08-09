package com.kodilla.currency.repository;

import com.kodilla.currency.entity.Code;
import com.kodilla.currency.entity.Favorite;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface FavoriteRepository extends CrudRepository<Favorite, Long> {

    @Override
    List<Favorite> findAll();

    @Override
    Optional<Favorite> findById(Long id);

    @Override
    Favorite save(Favorite favorite);

    @Override
    void deleteById(Long id);

    @Query
    List<Favorite> findDuplicates(@Param("NAME") String name, @Param("CODE") Code code);
}

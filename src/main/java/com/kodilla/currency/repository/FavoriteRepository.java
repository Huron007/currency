package com.kodilla.currency.repository;

import com.kodilla.currency.entity.Favorite;
import org.springframework.data.repository.CrudRepository;
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
}

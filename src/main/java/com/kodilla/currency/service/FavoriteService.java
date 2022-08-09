package com.kodilla.currency.service;

import com.kodilla.currency.entity.Favorite;
import com.kodilla.currency.exception.FavoriteNotFoundException;
import com.kodilla.currency.repository.FavoriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;

    public List<Favorite> getAllFavorites(){
        return favoriteRepository.findAll();
    }

    public Favorite getFavorite(final Long favoriteId) throws FavoriteNotFoundException {
        if(favoriteRepository.existsById(favoriteId)){
            if(favoriteRepository.findById(favoriteId).isPresent()){
                return favoriteRepository.findById(favoriteId).get();
            } else throw new FavoriteNotFoundException();
        } else throw new FavoriteNotFoundException();
    }

    public Favorite saveFavorite(final Favorite favorite) {
        return favoriteRepository.save(favorite);
    }

    public List<Favorite> saveFavoriteList(final List<Favorite> favoriteList) {
        return favoriteList.stream()
                .map(favoriteRepository::save)
                .collect(Collectors.toList());
    }

    public Favorite updateFavorite(final Favorite favorite) throws FavoriteNotFoundException {
        if(favoriteRepository.existsById(favorite.getId())){
            return favoriteRepository.save(favorite);
        } else throw new FavoriteNotFoundException();
    }

    public List<Favorite> updateFavoriteList(final List<Favorite> favoriteList) throws FavoriteNotFoundException {
        if(favoriteList.stream().map(e -> favoriteRepository.findById(e.getId()).isPresent()).count() == favoriteList.size()){
            return favoriteList.stream()
                    .map(favoriteRepository::save)
                    .collect(Collectors.toList());
        } else throw new FavoriteNotFoundException();
    }

    public void deleteFavorite(final Long favoriteId) throws FavoriteNotFoundException {
        if(favoriteRepository.existsById(favoriteId)){
            favoriteRepository.deleteById(favoriteId);
        } else throw new FavoriteNotFoundException();
    }
}

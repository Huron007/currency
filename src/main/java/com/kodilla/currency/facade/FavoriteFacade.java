package com.kodilla.currency.facade;

import com.kodilla.currency.dto.FavoriteDto;
import com.kodilla.currency.exception.DuplicateFavoriteException;
import com.kodilla.currency.exception.FavoriteNotFoundException;
import com.kodilla.currency.mapper.FavoriteMapper;
import com.kodilla.currency.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FavoriteFacade {
    
    private final FavoriteService favoriteService;
    private final FavoriteMapper favoriteMapper;

    public List<FavoriteDto> getAllFavorites(){
        return favoriteMapper.mapToFavoriteDtoList(favoriteService.getAllFavorites());
    }

    public FavoriteDto getSingleFavorite(final Long favoriteId) throws FavoriteNotFoundException {
        return favoriteMapper.mapToFavoriteDto(favoriteService.getFavorite(favoriteId));
    }

    public FavoriteDto saveSingleFavorite(final FavoriteDto favoriteDto) throws DuplicateFavoriteException {
        return favoriteMapper.mapToFavoriteDto(favoriteService.saveFavorite(favoriteMapper.mapToFavorite(favoriteDto)));
    }

    public List<FavoriteDto> saveFavoriteList(final List<FavoriteDto> favoriteDtoList){
        return favoriteMapper.mapToFavoriteDtoList(favoriteService.saveFavoriteList(favoriteMapper.mapToFavoriteList(favoriteDtoList)));
    }

    public FavoriteDto updateSingleFavorite(final FavoriteDto favoriteDto) throws FavoriteNotFoundException {
        return favoriteMapper.mapToFavoriteDto(favoriteService.updateFavorite(favoriteMapper.mapToFavorite(favoriteDto)));
    }

    public List<FavoriteDto> updateFavoriteList(final List<FavoriteDto> favoriteDtoList) throws FavoriteNotFoundException {
        return favoriteMapper.mapToFavoriteDtoList(favoriteService.updateFavoriteList(favoriteMapper.mapToFavoriteList(favoriteDtoList)));
    }

    public void deleteFavorite(final Long favoriteId) throws FavoriteNotFoundException {
        favoriteService.deleteFavorite(favoriteId);
    }
}

package com.kodilla.currency.facade;

import com.kodilla.currency.dto.FavoriteDto;
import com.kodilla.currency.entity.Code;
import com.kodilla.currency.entity.Favorite;
import com.kodilla.currency.exception.DuplicateFavoriteException;
import com.kodilla.currency.exception.FavoriteNotFoundException;
import com.kodilla.currency.mapper.FavoriteMapper;
import com.kodilla.currency.service.FavoriteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FavoriteFacadeTest {

    @InjectMocks
    private FavoriteFacade favoriteFacade;

    @Mock
    private FavoriteService favoriteService;

    @Mock
    private FavoriteMapper favoriteMapper;

    @Test
    void getAllFavorites() {
        //Given
        FavoriteDto favoriteDto = new FavoriteDto(1L, "test", Code.USD);
        List<FavoriteDto> dtoList = List.of(favoriteDto);
        Favorite favorite = new Favorite(1L, "test", Code.USD);
        List<Favorite> list = List.of(favorite);
        when(favoriteService.getAllFavorites()).thenReturn(list);
        when(favoriteMapper.mapToFavoriteDtoList(anyList())).thenReturn(dtoList);
        //When
        List<FavoriteDto> result = favoriteFacade.getAllFavorites();
        //Then
        assertThat(result).isNotNull();
        assertEquals(dtoList, result);
    }

    @Test
    void getSingleFavorite() throws FavoriteNotFoundException {
        //Given
        FavoriteDto favoriteDto = new FavoriteDto(1L, "test", Code.USD);
        Favorite favorite = new Favorite(1L, "test", Code.USD);
        when(favoriteService.getFavorite(1L)).thenReturn(favorite);
        when(favoriteMapper.mapToFavoriteDto(any(Favorite.class))).thenReturn(favoriteDto);
        //When
        FavoriteDto result = favoriteFacade.getSingleFavorite(1L);
        //Then
        assertEquals(favoriteDto, result);
    }

    @Test
    void saveSingleFavorite() throws DuplicateFavoriteException {
        //Given
        FavoriteDto favoriteDto = new FavoriteDto(1L, "test", Code.USD);
        Favorite favorite = new Favorite(1L, "test", Code.USD);
        when(favoriteService.saveFavorite(any(Favorite.class))).thenReturn(favorite);
        when(favoriteMapper.mapToFavoriteDto(any(Favorite.class))).thenReturn(favoriteDto);
        when(favoriteMapper.mapToFavorite(any(FavoriteDto.class))).thenReturn(favorite);
        //When
        FavoriteDto result = favoriteFacade.saveSingleFavorite(favoriteDto);
        //Then
        assertEquals(favoriteDto, result);
    }

    @Test
    void saveFavoriteList() {
        //Given
        FavoriteDto favoriteDto = new FavoriteDto(1L, "test", Code.USD);
        List<FavoriteDto> dtoList = List.of(favoriteDto);
        Favorite favorite = new Favorite(1L, "test", Code.USD);
        List<Favorite> list = List.of(favorite);
        when(favoriteService.saveFavoriteList(anyList())).thenReturn(list);
        when(favoriteMapper.mapToFavoriteDtoList(anyList())).thenReturn(dtoList);
        when(favoriteMapper.mapToFavoriteList(anyList())).thenReturn(list);
        //When
        List<FavoriteDto> result = favoriteFacade.saveFavoriteList(dtoList);
        //Then
        assertThat(result).isNotNull();
        assertEquals(dtoList, result);
    }

    @Test
    void updateSingleFavorite() throws FavoriteNotFoundException {
        //Given
        FavoriteDto favoriteDto = new FavoriteDto(1L, "test", Code.USD);
        Favorite favorite = new Favorite(1L, "test", Code.USD);
        when(favoriteService.updateFavorite(any(Favorite.class))).thenReturn(favorite);
        when(favoriteMapper.mapToFavoriteDto(any(Favorite.class))).thenReturn(favoriteDto);
        when(favoriteMapper.mapToFavorite(any(FavoriteDto.class))).thenReturn(favorite);
        //When
        FavoriteDto result = favoriteFacade.updateSingleFavorite(favoriteDto);
        //Then
        assertEquals(favoriteDto, result);
    }

    @Test
    void updateFavoriteList() throws FavoriteNotFoundException {
        //Given
        FavoriteDto favoriteDto = new FavoriteDto(1L, "test", Code.USD);
        List<FavoriteDto> dtoList = List.of(favoriteDto);
        Favorite favorite = new Favorite(1L, "test", Code.USD);
        List<Favorite> list = List.of(favorite);
        when(favoriteService.updateFavoriteList(anyList())).thenReturn(list);
        when(favoriteMapper.mapToFavoriteDtoList(anyList())).thenReturn(dtoList);
        when(favoriteMapper.mapToFavoriteList(anyList())).thenReturn(list);
        //When
        List<FavoriteDto> result = favoriteFacade.updateFavoriteList(dtoList);
        //Then
        assertThat(result).isNotNull();
        assertEquals(dtoList, result);
    }

    @Test
    void deleteFavorite() throws FavoriteNotFoundException {
        //Given & When
        favoriteFacade.deleteFavorite(1L);
        //Then
        verify(favoriteService, times(1)).deleteFavorite(1L);
    }
}
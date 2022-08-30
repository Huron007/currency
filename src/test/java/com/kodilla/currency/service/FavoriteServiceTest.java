package com.kodilla.currency.service;

import com.kodilla.currency.entity.Favorite;
import com.kodilla.currency.entity.Code;
import com.kodilla.currency.exception.DuplicateFavoriteException;
import com.kodilla.currency.exception.FavoriteNotFoundException;
import com.kodilla.currency.repository.FavoriteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FavoriteServiceTest {

    @InjectMocks
    private FavoriteService favoriteService;

    @Mock
    private FavoriteRepository favoriteRepository;

    @Test
    void getAllFavorites() {
        //Given
        Favorite favorite = new Favorite(1L, "test", Code.USD);
        List<Favorite> list = List.of(favorite);
        when(favoriteRepository.findAll()).thenReturn(list);
        //When
        List<Favorite> result = favoriteService.getAllFavorites();
        //Then
        assertEquals(list, result);
    }

    @Test
    void getFavorite() throws FavoriteNotFoundException {
        //Given
        Favorite favorite = new Favorite(1L, "test", Code.USD);
        when(favoriteRepository.existsById(1L)).thenReturn(true);
        when(favoriteRepository.findById(1L)).thenReturn(Optional.of(favorite));
        //When
        Favorite result = favoriteService.getFavorite(1L);
        //Then
        assertEquals(favorite, result);
    }

    @Test
    void saveFavorite() throws DuplicateFavoriteException {
        //Given
        Favorite favorite = new Favorite(1L, "test", Code.USD);
        when(favoriteRepository.save(any(Favorite.class))).thenReturn(favorite);
        //When
        Favorite result = favoriteService.saveFavorite(favorite);
        //Then
        assertEquals(favorite, result);
    }

    @Test
    void saveFavoriteList() {
        //Given
        Favorite favorite = new Favorite(1L, "test", Code.USD);
        List<Favorite> list = List.of(favorite);
        when(favoriteRepository.save(any(Favorite.class))).thenReturn(favorite);
        //When
        List<Favorite> result = favoriteService.saveFavoriteList(list);
        //Then
        assertEquals(list, result);
    }
    @Test
    void updateFavorite() throws FavoriteNotFoundException {
        //Given
        Favorite favorite = new Favorite(1L, "test", Code.USD);
        when(favoriteRepository.save(any(Favorite.class))).thenReturn(favorite);
        when(favoriteRepository.existsById(1L)).thenReturn(true);
        //When
        Favorite result = favoriteService.updateFavorite(favorite);
        //Then
        assertEquals(favorite, result);
    }

    @Test
    void updateFavoriteList() throws FavoriteNotFoundException {
        //Given
        Favorite favorite = new Favorite(1L, "test", Code.USD);
        List<Favorite> list = List.of(favorite);
        when(favoriteRepository.save(any(Favorite.class))).thenReturn(favorite);
        //When
        List<Favorite> result = favoriteService.updateFavoriteList(list);
        //Then
        assertEquals(list, result);
    }

    @Test
    void deleteFavorite() throws FavoriteNotFoundException {
        //Given
        when(favoriteRepository.existsById(1L)).thenReturn(true);
        //When
        favoriteService.deleteFavorite(1L);
        //Then
        verify(favoriteRepository, times(1)).deleteById(1L);
    }
}
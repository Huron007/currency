package com.kodilla.currency.mapper;

import com.kodilla.currency.dto.FavoriteDto;
import com.kodilla.currency.entity.Code;
import com.kodilla.currency.entity.Favorite;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FavoriteMapperTest {

    @Autowired
    private FavoriteMapper mapper = new FavoriteMapper();

    @Test
    void mapToFavorite() {
        //Given
        Favorite favorite = new Favorite(1L, Code.USD.name, Code.USD);
        FavoriteDto favoriteDto = new FavoriteDto(1L, "test", Code.USD);
        //When
        Favorite mappedFavorite = mapper.mapToFavorite(favoriteDto);
        //Then
        assertEquals(favorite, mappedFavorite);
    }

    @Test
    void mapToFavoriteDto() {
        //Given
        Favorite favorite = new Favorite(1L, "test", Code.USD);
        FavoriteDto favoriteDto = new FavoriteDto(1L, "test", Code.USD);
        //When
        FavoriteDto mappedFavorite = mapper.mapToFavoriteDto(favorite);
        //Then
        assertEquals(favoriteDto, mappedFavorite);
    }

    @Test
    void mapToFavoriteList() {
        //Given
        Favorite favorite = new Favorite(1L, Code.USD.name, Code.USD);
        FavoriteDto favoriteDto = new FavoriteDto(1L, "test", Code.USD);
        List<Favorite> list = List.of(favorite);
        List<FavoriteDto> dtoList = List.of(favoriteDto);
        //When
        List<Favorite> mappedList = mapper.mapToFavoriteList(dtoList);
        //Then
        assertEquals(list, mappedList);
    }

    @Test
    void mapToFavoriteDtoList() {
        //Given
        Favorite favorite = new Favorite(1L, "test", Code.USD);
        FavoriteDto favoriteDto = new FavoriteDto(1L, "test", Code.USD);
        List<Favorite> list = List.of(favorite);
        List<FavoriteDto> dtoList = List.of(favoriteDto);
        //When
        List<FavoriteDto> mappedList = mapper.mapToFavoriteDtoList(list);
        //Then
        assertEquals(dtoList, mappedList);
    }
}
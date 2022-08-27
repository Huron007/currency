package com.kodilla.currency.mapper;

import com.kodilla.currency.dto.FavoriteDto;
import com.kodilla.currency.entity.Favorite;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FavoriteMapper {

    public Favorite mapToFavorite(FavoriteDto favoriteDto){
        if(favoriteDto.getId() == null){
            return Favorite.builder()
                    .id(0L)
                    .name(favoriteDto.getCode().name)
                    .code(favoriteDto.getCode())
                    .build();
        } else {
            return Favorite.builder()
                    .id(favoriteDto.getId())
                    .name(favoriteDto.getCode().name)
                    .code(favoriteDto.getCode())
                    .build();
        }
    }

    public FavoriteDto mapToFavoriteDto(Favorite favorite){
        return new FavoriteDto(
                favorite.getId(),
                favorite.getName(),
                favorite.getCode()
        );
    }

    public List<Favorite> mapToFavoriteList(List<FavoriteDto> favoriteDtoList){
        return favoriteDtoList.stream()
                .map(this::mapToFavorite)
                .collect(Collectors.toList());
    }

    public List<FavoriteDto> mapToFavoriteDtoList(List<Favorite> favoriteList){
        return favoriteList.stream()
                .map(this::mapToFavoriteDto)
                .collect(Collectors.toList());
    }
}

package com.kodilla.currency.controller;

import com.kodilla.currency.dto.FavoriteDto;
import com.kodilla.currency.exception.DuplicateFavoriteException;
import com.kodilla.currency.exception.FavoriteNotFoundException;
import com.kodilla.currency.facade.FavoriteFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/v1/favorite")
public class FavoriteController {
    
    private final FavoriteFacade favoriteFacade;

    @GetMapping
    public ResponseEntity<List<FavoriteDto>> getFavorites(){
        return ResponseEntity.ok(favoriteFacade.getAllFavorites());
    }

    @GetMapping(value = "{favoriteId}")
    public ResponseEntity<FavoriteDto> getFavorite(@PathVariable Long favoriteId) throws FavoriteNotFoundException {
        return ResponseEntity.ok(favoriteFacade.getSingleFavorite(favoriteId));
    }

    @DeleteMapping(value = "{favoriteId}")
    public ResponseEntity<Void> deleteFavorite(@PathVariable Long favoriteId) throws FavoriteNotFoundException {
        favoriteFacade.deleteFavorite(favoriteId);
        return ResponseEntity.ok().build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FavoriteDto> createFavorite(@RequestBody FavoriteDto favoriteDto) throws DuplicateFavoriteException {
        return ResponseEntity.ok(favoriteFacade.saveSingleFavorite(favoriteDto));
    }

    @PostMapping(value = "FavoriteList",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FavoriteDto>> createFavoriteList(@RequestBody List<FavoriteDto> favoriteDtoList) {
        return ResponseEntity.ok(favoriteFacade.saveFavoriteList(favoriteDtoList));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FavoriteDto> updateFavorite(@RequestBody FavoriteDto favoriteDto) throws FavoriteNotFoundException {
        return ResponseEntity.ok(favoriteFacade.updateSingleFavorite(favoriteDto));
    }

    @PutMapping(value = "FavoriteList", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FavoriteDto>> updateFavoriteList(@RequestBody List<FavoriteDto> favoriteDtoList) throws FavoriteNotFoundException {
        return ResponseEntity.ok(favoriteFacade.updateFavoriteList(favoriteDtoList));
    }
}

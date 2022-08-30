package com.kodilla.currency.controller;

import com.google.gson.*;
import com.kodilla.currency.dto.FavoriteDto;
import com.kodilla.currency.entity.Code;
import com.kodilla.currency.facade.FavoriteFacade;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(FavoriteController.class)
class FavoriteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FavoriteFacade favoriteFacade;

    class LocalDateAdapter implements JsonSerializer<LocalDate> {

        @Override
        public JsonElement serialize(LocalDate date, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(date.format(DateTimeFormatter.ISO_LOCAL_DATE));
        }
    }

    @Test
    void shouldGetFavorites() throws Exception {
        //Given
        FavoriteDto favoriteDto = new FavoriteDto(1L, "test", Code.USD);
        List<FavoriteDto> list = List.of(favoriteDto);
        when(favoriteFacade.getAllFavorites()).thenReturn(list);
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/favorite")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("test")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].code", Matchers.is(Code.USD.toString())));
    }

    @Test
    void shouldGetFavorite() throws Exception {
        //Given
        FavoriteDto favoriteDto = new FavoriteDto(1L, "test", Code.USD);
        when(favoriteFacade.getSingleFavorite(1L)).thenReturn(favoriteDto);
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/favorite/{favoriteId}", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("test")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(Code.USD.toString())));
    }

    @Test
    void shouldDeleteFavorite() throws Exception {
        //Given
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/v1/favorite/{favoriteId}", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void shouldCreateFavorite() throws Exception {
        //Given
        FavoriteDto favoriteDto = new FavoriteDto(1L, "test", Code.USD);
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        String jsonContent = gson.toJson(favoriteDto);
        when(favoriteFacade.saveSingleFavorite(any(FavoriteDto.class))).thenReturn(favoriteDto);
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/v1/favorite")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent)
                        .characterEncoding("UTF-8"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("test")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(Code.USD.toString())));
    }

    @Test
    void shouldCreateFavoriteList() throws Exception {
        //Given
        FavoriteDto favoriteDto = new FavoriteDto(1L, "test", Code.USD);
        List<FavoriteDto> list = List.of(favoriteDto);
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        String jsonContent = gson.toJson(list);
        when(favoriteFacade.saveFavoriteList(anyList())).thenReturn(list);
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/v1/favorite/FavoriteList")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent)
                        .characterEncoding("UTF-8"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("test")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].code", Matchers.is(Code.USD.toString())));
    }

    @Test
    void shouldUpdateFavorite() throws Exception {
        //Given
        FavoriteDto favoriteDto = new FavoriteDto(1L, "test", Code.USD);
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        String jsonContent = gson.toJson(favoriteDto);
        when(favoriteFacade.updateSingleFavorite(any(FavoriteDto.class))).thenReturn(favoriteDto);
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/v1/favorite")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent)
                        .characterEncoding("UTF-8"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("test")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(Code.USD.toString())));
    }

    @Test
    void shouldUpdateFavoriteList() throws Exception {
        //Given
        FavoriteDto favoriteDto = new FavoriteDto(1L, "test", Code.USD);
        List<FavoriteDto> list = List.of(favoriteDto);
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        String jsonContent = gson.toJson(list);
        when(favoriteFacade.updateFavoriteList(anyList())).thenReturn(list);
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/v1/favorite/FavoriteList")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent)
                        .characterEncoding("UTF-8"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("test")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].code", Matchers.is(Code.USD.toString())));
    }
}
package com.kodilla.currency.controller;

import com.google.gson.*;
import com.kodilla.currency.dto.AlertDto;
import com.kodilla.currency.dto.CurrencyDto;
import com.kodilla.currency.entity.Code;
import com.kodilla.currency.facade.AlertFacade;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(AlertController.class)
class AlertControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlertFacade alertFacade;

    class LocalDateAdapter implements JsonSerializer<LocalDate> {

        @Override
        public JsonElement serialize(LocalDate date, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(date.format(DateTimeFormatter.ISO_LOCAL_DATE));
        }
    }

    @Test
    void shouldGetAlerts() throws Exception {
        AlertDto alertDto = new AlertDto(1L, "test", Code.USD, 1.0, LocalDate.now(), true);
        List<AlertDto> list = List.of(alertDto);
        when(alertFacade.getAllAlerts()).thenReturn(list);
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/alert")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("test")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].code", Matchers.is(Code.USD.toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].trackedMargin", Matchers.is(1.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].creationDate", Matchers.is(LocalDate.now().toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].active", Matchers.is(true)));
    }

    @Test
    void shouldGetAlert() throws Exception {
        //Given
        AlertDto alertDto = new AlertDto(1L, "test", Code.USD, 1.0, LocalDate.now(), true);
        when(alertFacade.getSingleAlert(1L)).thenReturn(alertDto);
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/alert/{alertId}", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("test")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(Code.USD.toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.trackedMargin", Matchers.is(1.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.creationDate", Matchers.is(LocalDate.now().toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.active", Matchers.is(true)));
    }

    @Test
    void shouldDeleteAlert() throws Exception {
        //Given
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/v1/alert/{alertId}", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void shouldCreateAlert() throws Exception {
        //Given
        AlertDto alertDto = new AlertDto(1L, "test", Code.USD, 1.0, LocalDate.now(), true);
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        String jsonContent = gson.toJson(alertDto);
        when(alertFacade.saveSingleAlert(any(AlertDto.class))).thenReturn(alertDto);
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/v1/alert")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent)
                        .characterEncoding("UTF-8"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("test")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(Code.USD.toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.trackedMargin", Matchers.is(1.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.creationDate", Matchers.is(LocalDate.now().toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.active", Matchers.is(true)));
    }

    @Test
    void shouldCreateAlertList() throws Exception {
        //Given
        AlertDto alertDto = new AlertDto(1L, "test", Code.USD, 1.0, LocalDate.now(), true);
        List<AlertDto> list = List.of(alertDto);
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        String jsonContent = gson.toJson(list);
        when(alertFacade.saveAlertList(anyList())).thenReturn(list);
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/v1/alert/AlertList")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent)
                        .characterEncoding("UTF-8"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("test")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].code", Matchers.is(Code.USD.toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].trackedMargin", Matchers.is(1.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].creationDate", Matchers.is(LocalDate.now().toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].active", Matchers.is(true)));
    }

    @Test
    void shouldUpdateAlert() throws Exception {
        //Given
        AlertDto alertDto = new AlertDto(1L, "test", Code.USD, 1.0, LocalDate.now(), true);
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        String jsonContent = gson.toJson(alertDto);
        when(alertFacade.updateSingleAlert(any(AlertDto.class))).thenReturn(alertDto);
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/v1/alert")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent)
                        .characterEncoding("UTF-8"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("test")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(Code.USD.toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.trackedMargin", Matchers.is(1.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.creationDate", Matchers.is(LocalDate.now().toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.active", Matchers.is(true)));
    }

    @Test
    void shouldUpdateAlertList() throws Exception {
        //Given
        AlertDto alertDto = new AlertDto(1L, "test", Code.USD, 1.0, LocalDate.now(), true);
        List<AlertDto> list = List.of(alertDto);
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        String jsonContent = gson.toJson(list);
        when(alertFacade.updateAlertList(anyList())).thenReturn(list);
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/v1/alert/AlertList")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent)
                        .characterEncoding("UTF-8"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("test")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].code", Matchers.is(Code.USD.toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].trackedMargin", Matchers.is(1.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].creationDate", Matchers.is(LocalDate.now().toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].active", Matchers.is(true)));
    }
}
package com.kodilla.currency.controller;

import com.google.gson.*;
import com.kodilla.currency.dto.CryptoCurrencyDto;
import com.kodilla.currency.dto.CurrencyDto;
import com.kodilla.currency.entity.Code;
import com.kodilla.currency.facade.CryptoCurrencyFacade;
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
@WebMvcTest(CryptoCurrencyController.class)
class CryptoCurrencyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CryptoCurrencyFacade cryptoCurrencyFacade;

    class LocalDateAdapter implements JsonSerializer<LocalDate> {

        @Override
        public JsonElement serialize(LocalDate date, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(date.format(DateTimeFormatter.ISO_LOCAL_DATE));
        }
    }

    @Test
    void shouldFetchEmptyCryptoCurrencyList() throws Exception {
        //Given
        when(cryptoCurrencyFacade.getAllCurrencies()).thenReturn(List.of());
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/cryptoCurrency")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    void shouldGetCurrencies() throws Exception {
        //Given
        CryptoCurrencyDto cryptoCurrencyDto = new CryptoCurrencyDto(1L, "test", Code.AUD, LocalDate.of(2000,1,1), 1.0);
        List<CryptoCurrencyDto> list = List.of(cryptoCurrencyDto);
        when(cryptoCurrencyFacade.getAllCurrencies()).thenReturn(list);
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/cryptoCurrency")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("test")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].code", Matchers.is(Code.AUD.toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].effectiveDate", Matchers.is(LocalDate.of(2000,1,1).toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].exchangeRate", Matchers.is(1.0)));
    }

    @Test
    void shouldGetAllCurrenciesWithGivenCode() throws Exception {
        //Given
        CryptoCurrencyDto cryptoCurrencyDto = new CryptoCurrencyDto(1L, "test", Code.AUD, LocalDate.of(2000,1,1), 1.0);
        List<CryptoCurrencyDto> list = List.of(cryptoCurrencyDto);
        when(cryptoCurrencyFacade.getAllCryptoCurrenciesWithGivenCode(Code.AUD)).thenReturn(list);
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/cryptoCurrency/list/{code}", Code.AUD)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("test")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].code", Matchers.is(Code.AUD.toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].effectiveDate", Matchers.is(LocalDate.of(2000,1,1).toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].exchangeRate", Matchers.is(1.0)));
    }

    @Test
    void shouldGetLatestCurrencyList() throws Exception {
        //Given
        CryptoCurrencyDto cryptoCurrencyDto = new CryptoCurrencyDto(1L, "test", Code.AUD, LocalDate.of(2000,1,1), 1.0);
        List<CryptoCurrencyDto> list = List.of(cryptoCurrencyDto);
        when(cryptoCurrencyFacade.getLatestCryptoCurrencyList()).thenReturn(list);
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/cryptoCurrency/list/latest")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("test")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].code", Matchers.is(Code.AUD.toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].effectiveDate", Matchers.is(LocalDate.of(2000,1,1).toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].exchangeRate", Matchers.is(1.0)));
    }

    @Test
    void shouldGetCryptoCurrency() throws Exception {
        //Given
        CryptoCurrencyDto cryptoCurrencyDto = new CryptoCurrencyDto(1L, "test", Code.AUD, LocalDate.of(2000,1,1), 1.0);
        when(cryptoCurrencyFacade.getSingleCryptoCurrency(1L)).thenReturn(cryptoCurrencyDto);
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/cryptoCurrency/{cryptoCurrencyId}", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("test")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(Code.AUD.toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.effectiveDate", Matchers.is(LocalDate.of(2000,1,1).toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.exchangeRate", Matchers.is(1.0)));
    }

    @Test
    void shouldDeleteCryptoCurrency() throws Exception {
        //Given
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/v1/cryptoCurrency/{cryptoCurrencyId}", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void shouldCreateCryptoCurrency() throws Exception {
        //Given
        CryptoCurrencyDto cryptoCurrencyDto = new CryptoCurrencyDto(1L, "test", Code.AUD, LocalDate.of(2000,1,1), 1.0);
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        String jsonContent = gson.toJson(cryptoCurrencyDto);
        when(cryptoCurrencyFacade.saveSingleCryptoCurrency(any(CryptoCurrencyDto.class))).thenReturn(cryptoCurrencyDto);
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/v1/cryptoCurrency")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent)
                        .characterEncoding("UTF-8"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("test")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(Code.AUD.toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.effectiveDate", Matchers.is(LocalDate.of(2000,1,1).toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.exchangeRate", Matchers.is(1.0)));
    }

    @Test
    void shouldCreateCryptoCurrencyTable() throws Exception {
        //Given
        CryptoCurrencyDto cryptoCurrencyDto = new CryptoCurrencyDto(1L, "test", Code.AUD, LocalDate.of(2000,1,1), 1.0);
        List<CryptoCurrencyDto> list = List.of(cryptoCurrencyDto);
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        String jsonContent = gson.toJson(list);
        when(cryptoCurrencyFacade.saveCryptoCurrencyTable(anyList())).thenReturn(list);
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/v1/cryptoCurrency/Table")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent)
                        .characterEncoding("UTF-8"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("test")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].code", Matchers.is(Code.AUD.toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].effectiveDate", Matchers.is(LocalDate.of(2000,1,1).toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].exchangeRate", Matchers.is(1.0)));
    }

    @Test
    void shouldUpdateCryptoCurrency() throws Exception {
        //Given
        CryptoCurrencyDto cryptoCurrencyDto = new CryptoCurrencyDto(1L, "test", Code.AUD, LocalDate.of(2000,1,1), 1.0);
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        String jsonContent = gson.toJson(cryptoCurrencyDto);
        when(cryptoCurrencyFacade.updateSingleCryptoCurrency(any(CryptoCurrencyDto.class))).thenReturn(cryptoCurrencyDto);
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/v1/cryptoCurrency")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent)
                        .characterEncoding("UTF-8"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("test")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(Code.AUD.toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.effectiveDate", Matchers.is(LocalDate.of(2000,1,1).toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.exchangeRate", Matchers.is(1.0)));
    }

    @Test
    void shouldUpdateTable() throws Exception {
        //Given
        CryptoCurrencyDto cryptoCurrencyDto = new CryptoCurrencyDto(1L, "test", Code.AUD, LocalDate.of(2000,1,1), 1.0);
        List<CryptoCurrencyDto> list = List.of(cryptoCurrencyDto);
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        String jsonContent = gson.toJson(list);
        when(cryptoCurrencyFacade.updateCryptoCurrencyTable(anyList())).thenReturn(list);
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/v1/cryptoCurrency/Table")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent)
                        .characterEncoding("UTF-8"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("test")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].code", Matchers.is(Code.AUD.toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].effectiveDate", Matchers.is(LocalDate.of(2000,1,1).toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].exchangeRate", Matchers.is(1.0)));
    }

    @Test
    void shouldFetchTopTenCrypto() throws Exception {
        //Given
        CryptoCurrencyDto cryptoCurrencyDto = new CryptoCurrencyDto(1L, "test", Code.AUD, LocalDate.of(2000,1,1), 1.0);
        List<CryptoCurrencyDto> list = List.of(cryptoCurrencyDto);
        when(cryptoCurrencyFacade.fetchTopTenCryptoList()).thenReturn(list);
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/cryptoCurrency/fetch/topTen")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("test")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].code", Matchers.is(Code.AUD.toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].effectiveDate", Matchers.is(LocalDate.of(2000,1,1).toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].exchangeRate", Matchers.is(1.0)));
    }

    @Test
    void shouldFetchCryptoFromWholeMonth() throws Exception {
        //Given
        CryptoCurrencyDto cryptoCurrencyDto = new CryptoCurrencyDto(1L, "test", Code.AUD, LocalDate.of(2000,1,1), 1.0);
        List<CryptoCurrencyDto> list = List.of(cryptoCurrencyDto);
        when(cryptoCurrencyFacade.fetchSingleCryptoFromWholeMonth(Code.AUD)).thenReturn(list);
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/cryptoCurrency/fetch/{code}", Code.AUD)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("test")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].code", Matchers.is(Code.AUD.toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].effectiveDate", Matchers.is(LocalDate.of(2000,1,1).toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].exchangeRate", Matchers.is(1.0)));
    }
}
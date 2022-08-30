package com.kodilla.currency.controller;

import com.google.gson.*;
import com.kodilla.currency.dto.CurrencyDto;
import com.kodilla.currency.entity.Code;
import com.kodilla.currency.facade.CurrencyFacade;
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
@WebMvcTest(CurrencyController.class)
class CurrencyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CurrencyFacade currencyFacade;

    class LocalDateAdapter implements JsonSerializer<LocalDate> {

        @Override
        public JsonElement serialize(LocalDate date, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(date.format(DateTimeFormatter.ISO_LOCAL_DATE));
        }
    }

    @Test
    void shouldFetchEmptyCurrencyList() throws Exception {
        //Given
        when(currencyFacade.getAllCurrencies()).thenReturn(List.of());
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/currency")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    void shouldGetCurrencyList() throws Exception {
        //Given
        CurrencyDto currencyDto = new CurrencyDto(1L, "test", Code.AUD, LocalDate.of(2000,1,1), 1.0);
        List<CurrencyDto> list = List.of(currencyDto);
        when(currencyFacade.getAllCurrencies()).thenReturn(list);
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/currency")
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
        CurrencyDto currencyDto = new CurrencyDto(1L, "test", Code.AUD, LocalDate.of(2000,1,1), 1.0);
        List<CurrencyDto> list = List.of(currencyDto);
        when(currencyFacade.getAllCurrenciesWithGivenCode(Code.AUD)).thenReturn(list);
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/currency/list/{code}", Code.AUD)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("test")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].code", Matchers.is(Code.AUD.toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].effectiveDate", Matchers.is(LocalDate.of(2000,1,1).toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].exchangeRate", Matchers.is(1.0)));
    }

    @Test
    void shouldFetchLatestCurrencyList() throws Exception {
        //Given
        CurrencyDto currencyDto = new CurrencyDto(1L, "test", Code.AUD, LocalDate.of(2000,1,1), 1.0);
        List<CurrencyDto> list = List.of(currencyDto);
        when(currencyFacade.getLatestCurrencyList()).thenReturn(list);
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/currency/list/latest")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("test")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].code", Matchers.is(Code.AUD.toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].effectiveDate", Matchers.is(LocalDate.of(2000,1,1).toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].exchangeRate", Matchers.is(1.0)));
    }

    @Test
    void shouldFetchSingleCurrency() throws Exception {
        //Given
        CurrencyDto currencyDto = new CurrencyDto(1L, "test", Code.AUD, LocalDate.of(2000,1,1), 1.0);
        when(currencyFacade.getSingleCurrency(1L)).thenReturn(currencyDto);
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/currency/{currencyId}","1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("test")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(Code.AUD.toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.effectiveDate", Matchers.is(LocalDate.of(2000,1,1).toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.exchangeRate", Matchers.is(1.0)));
    }

    @Test
    void shouldGetLatestExchangeRate() throws Exception {
        //Given
        Double rate = 1.0;
        Code code = Code.USD;
        when(currencyFacade.getLatestExchangeRate(code)).thenReturn(rate);
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                    .get("/v1/currency/rate/{code}", code)
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.is(1.0)));
    }

    @Test
    void shouldCalculate() throws Exception {
        //Given
        Double value = 1.0;
        Double result = 1.0;
        Code codeOne = Code.USD;
        Code codeTwo = Code.EUR;
        when(currencyFacade.calculate(codeOne, codeTwo, value)).thenReturn(result);
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/currency/calculate")
                        .param("code1", String.valueOf(codeOne))
                        .param("code2", String.valueOf(codeTwo))
                        .param("value", String.valueOf(value))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.is(1.0)));
    }

    @Test
    void shouldDeleteCurrency() throws Exception {
        //Given
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/v1/currency/{currencyId}", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void shouldCreateCurrency() throws Exception {
        //Given
        CurrencyDto currencyDto = new CurrencyDto(1L, "test", Code.USD, LocalDate.now(), 1.0);
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        String jsonContent = gson.toJson(currencyDto);
        when(currencyFacade.saveSingleCurrency(any(CurrencyDto.class))).thenReturn(currencyDto);
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/v1/currency")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent)
                        .characterEncoding("UTF-8"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("test")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(Code.USD.toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.effectiveDate", Matchers.is(LocalDate.now().toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.exchangeRate", Matchers.is(1.0)));
    }

    @Test
    void shouldCreateCurrencyTable() throws Exception {
        //Given
        CurrencyDto currencyDto = new CurrencyDto(1L, "test", Code.USD, LocalDate.now(), 1.0);
        List<CurrencyDto> list = List.of(currencyDto);
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        String jsonContent = gson.toJson(list);
        when(currencyFacade.saveCurrencyTable(anyList())).thenReturn(list);
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/v1/currency/Table")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent)
                        .characterEncoding("UTF-8"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("test")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].code", Matchers.is(Code.USD.toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].effectiveDate", Matchers.is(LocalDate.now().toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].exchangeRate", Matchers.is(1.0)));
    }

    @Test
    void shouldUpdateCurrency() throws Exception {
        //Given
        CurrencyDto currencyDto = new CurrencyDto(1L, "test", Code.USD, LocalDate.now(), 1.0);
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        String jsonContent = gson.toJson(currencyDto);
        when(currencyFacade.updateSingleCurrency(any(CurrencyDto.class))).thenReturn(currencyDto);
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/v1/currency")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent)
                        .characterEncoding("UTF-8"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("test")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(Code.USD.toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.effectiveDate", Matchers.is(LocalDate.now().toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.exchangeRate", Matchers.is(1.0)));
    }

    @Test
    void updateTable() throws Exception {
        //Given
        CurrencyDto currencyDto = new CurrencyDto(1L, "test", Code.USD, LocalDate.now(), 1.0);
        List<CurrencyDto> list = List.of(currencyDto);
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        String jsonContent = gson.toJson(list);
        when(currencyFacade.updateCurrencyTable(anyList())).thenReturn(list);
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/v1/currency/Table")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent)
                        .characterEncoding("UTF-8"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("test")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].code", Matchers.is(Code.USD.toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].effectiveDate", Matchers.is(LocalDate.now().toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].exchangeRate", Matchers.is(1.0)));
    }

    @Test
    void fetchSingleCurrency() throws Exception {
        //Given
        CurrencyDto currencyDto = new CurrencyDto(1L, "test", Code.AUD, LocalDate.of(2000,1,1), 1.0);
        when(currencyFacade.fetchSingleCurrency(Code.AUD)).thenReturn(currencyDto);
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/currency/fetch/{code}",Code.AUD)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("test")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(Code.AUD.toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.effectiveDate", Matchers.is(LocalDate.of(2000,1,1).toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.exchangeRate", Matchers.is(1.0)));
    }

    @Test
    void shouldFetchSingleCurrencyFromWholeMonth() throws Exception {
        //Given
        CurrencyDto currencyDto = new CurrencyDto(1L, "test", Code.AUD, LocalDate.of(2000,1,1), 1.0);
        List<CurrencyDto> list = List.of(currencyDto);
        when(currencyFacade.fetchSingleCurrencyFromWholeMonth(Code.AUD)).thenReturn(list);
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/currency/fetch/lastMonth/{code}", Code.AUD)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("test")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].code", Matchers.is(Code.AUD.toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].effectiveDate", Matchers.is(LocalDate.of(2000,1,1).toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].exchangeRate", Matchers.is(1.0)));
    }

    @Test
    void shouldFetchTable() throws Exception {
        //Given
        CurrencyDto currencyDto = new CurrencyDto(1L, "test", Code.AUD, LocalDate.of(2000,1,1), 1.0);
        List<CurrencyDto> list = List.of(currencyDto);
        when(currencyFacade.fetchCurrencyList()).thenReturn(list);
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/currency/fetch/table")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("test")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].code", Matchers.is(Code.AUD.toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].effectiveDate", Matchers.is(LocalDate.of(2000,1,1).toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].exchangeRate", Matchers.is(1.0)));
    }

    @Test
    void shouldFetchTableFromWholeMonth() throws Exception {
        //Given
        CurrencyDto currencyDto = new CurrencyDto(1L, "test", Code.AUD, LocalDate.of(2000,1,1), 1.0);
        List<CurrencyDto> list = List.of(currencyDto);
        when(currencyFacade.fetchCurrencyListFromWholeMonth()).thenReturn(list);
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/currency/fetch/lastMonth/table")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("test")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].code", Matchers.is(Code.AUD.toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].effectiveDate", Matchers.is(LocalDate.of(2000,1,1).toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].exchangeRate", Matchers.is(1.0)));
    }
}
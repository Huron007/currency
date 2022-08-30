package com.kodilla.currency.mapper;

import com.kodilla.currency.dto.AlertDto;
import com.kodilla.currency.entity.Alert;
import com.kodilla.currency.entity.Code;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AlertMapperTest {

    @Autowired
    private AlertMapper mapper = new AlertMapper();

    @Test
    void mapToAlert() {
        //Given
        Alert alert = new Alert(1L, Code.USD.name, Code.USD, 1.0, LocalDate.now(), true);
        AlertDto alertDto = new AlertDto(1L, "test", Code.USD, 1.0, LocalDate.now(), true);
        //When
        Alert mappedAlert = mapper.mapToAlert(alertDto);
        //Then
        assertEquals(alert, mappedAlert);
    }

    @Test
    void mapToAlertDto() {
        //Given
        Alert alert = new Alert(1L, "test", Code.USD, 1.0, LocalDate.now(), true);
        AlertDto alertDto = new AlertDto(1L, "test", Code.USD, 1.0, LocalDate.now(), true);
        //When
        AlertDto mappedAlert = mapper.mapToAlertDto(alert);
        //Then
        assertEquals(alertDto, mappedAlert);
    }

    @Test
    void mapToAlertList() {
        //Given
        Alert alert = new Alert(1L, Code.USD.name, Code.USD, 1.0, LocalDate.now(), true);
        AlertDto alertDto = new AlertDto(1L, "test", Code.USD, 1.0, LocalDate.now(), true);
        List<Alert> list = List.of(alert);
        List<AlertDto> dtoList = List.of(alertDto);
        //When
        List<Alert> mappedList = mapper.mapToAlertList(dtoList);
        //Then
        assertEquals(list, mappedList);
    }

    @Test
    void mapToAlertDtoList() {
        //Given
        Alert alert = new Alert(1L, "test", Code.USD, 1.0, LocalDate.now(), true);
        AlertDto alertDto = new AlertDto(1L, "test", Code.USD, 1.0, LocalDate.now(), true);
        List<Alert> list = List.of(alert);
        List<AlertDto> dtoList = List.of(alertDto);
        //When
        List<AlertDto> mappedList = mapper.mapToAlertDtoList(list);
        //Then
        assertEquals(dtoList, mappedList);
    }
}
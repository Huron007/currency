package com.kodilla.currency.facade;

import com.kodilla.currency.dto.AlertDto;
import com.kodilla.currency.entity.Alert;
import com.kodilla.currency.entity.Code;
import com.kodilla.currency.exception.AlertNotFoundException;
import com.kodilla.currency.mapper.AlertMapper;
import com.kodilla.currency.service.AlertService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AlertFacadeTest {

    @InjectMocks
    private AlertFacade alertFacade;

    @Mock
    private AlertService alertService;

    @Mock
    private AlertMapper alertMapper;


    @Test
    void getAllAlerts() {
        //Given
        AlertDto alertDto = new AlertDto(1L, "test", Code.USD, 1.0, LocalDate.now(), true);
        List<AlertDto> dtoList = List.of(alertDto);
        Alert alert = new Alert(1L, "test", Code.USD, 1.0, LocalDate.now(), true);
        List<Alert> list = List.of(alert);
        when(alertService.getAllAlerts()).thenReturn(list);
        when(alertMapper.mapToAlertDtoList(anyList())).thenReturn(dtoList);
        //When
        List<AlertDto> result = alertFacade.getAllAlerts();
        //Then
        assertThat(result).isNotNull();
        assertEquals(dtoList, result);
    }

    @Test
    void getSingleAlert() throws AlertNotFoundException {
        //Given
        Alert alert = new Alert(1L, "test", Code.USD, 1.0, LocalDate.now(), true);
        AlertDto alertDto = new AlertDto(1L, "test", Code.USD, 1.0, LocalDate.now(), true);
        when(alertService.getAlert(1L)).thenReturn(alert);
        when(alertMapper.mapToAlertDto(any(Alert.class))).thenReturn(alertDto);
        //When
        AlertDto result = alertFacade.getSingleAlert(1L);
        //Then
        assertEquals(alertDto, result);
    }

    @Test
    void saveSingleAlert() {
        //Given
        Alert alert = new Alert(1L, "test", Code.USD, 1.0, LocalDate.now(), true);
        AlertDto alertDto = new AlertDto(1L, "test", Code.USD, 1.0, LocalDate.now(), true);
        when(alertService.saveAlert(any(Alert.class))).thenReturn(alert);
        when(alertMapper.mapToAlert(any(AlertDto.class))).thenReturn(alert);
        when(alertMapper.mapToAlertDto(any(Alert.class))).thenReturn(alertDto);
        //When
        AlertDto result = alertFacade.saveSingleAlert(alertDto);
        //Then
        assertEquals(alertDto, result);
    }

    @Test
    void saveAlertList() {
        //Given
        AlertDto alertDto = new AlertDto(1L, "test", Code.USD, 1.0, LocalDate.now(), true);
        List<AlertDto> dtoList = List.of(alertDto);
        Alert alert = new Alert(1L, "test", Code.USD, 1.0, LocalDate.now(), true);
        List<Alert> list = List.of(alert);
        when(alertService.saveAlertList(anyList())).thenReturn(list);
        when(alertMapper.mapToAlertDtoList(anyList())).thenReturn(dtoList);
        when(alertMapper.mapToAlertList(anyList())).thenReturn(list);
        //When
        List<AlertDto> result = alertFacade.saveAlertList(dtoList);
        //Then
        assertThat(result).isNotNull();
        assertEquals(dtoList, result);

    }

    @Test
    void updateSingleAlert() throws AlertNotFoundException {
        //Given
        Alert alert = new Alert(1L, "test", Code.USD, 1.0, LocalDate.now(), true);
        AlertDto alertDto = new AlertDto(1L, "test", Code.USD, 1.0, LocalDate.now(), true);
        when(alertService.updateAlert(any(Alert.class))).thenReturn(alert);
        when(alertMapper.mapToAlert(any(AlertDto.class))).thenReturn(alert);
        when(alertMapper.mapToAlertDto(any(Alert.class))).thenReturn(alertDto);
        //When
        AlertDto result = alertFacade.updateSingleAlert(alertDto);
        //Then
        assertEquals(alertDto, result);
    }

    @Test
    void updateAlertList() throws AlertNotFoundException {
        //Given
        AlertDto alertDto = new AlertDto(1L, "test", Code.USD, 1.0, LocalDate.now(), true);
        List<AlertDto> dtoList = List.of(alertDto);
        Alert alert = new Alert(1L, "test", Code.USD, 1.0, LocalDate.now(), true);
        List<Alert> list = List.of(alert);
        when(alertService.updateAlertList(anyList())).thenReturn(list);
        when(alertMapper.mapToAlertDtoList(anyList())).thenReturn(dtoList);
        when(alertMapper.mapToAlertList(anyList())).thenReturn(list);
        //When
        List<AlertDto> result = alertFacade.updateAlertList(dtoList);
        //Then
        assertThat(result).isNotNull();
        assertEquals(dtoList, result);
    }

    @Test
    void deleteAlert() throws AlertNotFoundException {
        //Given & When
        alertFacade.deleteAlert(1L);
        //Then
        verify(alertService, times(1)).deleteAlert(1L);
    }
}
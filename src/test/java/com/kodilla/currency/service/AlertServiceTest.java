package com.kodilla.currency.service;

import com.kodilla.currency.entity.Alert;
import com.kodilla.currency.entity.Code;
import com.kodilla.currency.exception.AlertNotFoundException;
import com.kodilla.currency.repository.AlertRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AlertServiceTest {

    @InjectMocks
    private AlertService alertService;

    @Mock
    private AlertRepository alertRepository;

    @Test
    void getAllAlerts() {
        //Given
        Alert alert = new Alert(1L, "test", Code.USD, 1.0, LocalDate.now(), true);
        List<Alert> list = List.of(alert);
        when(alertRepository.findAll()).thenReturn(list);
        //When
        List<Alert> result = alertService.getAllAlerts();
        //Then
        assertEquals(list, result);
    }

    @Test
    void getAlert() throws AlertNotFoundException {
        //Given
        Alert alert = new Alert(1L, "test", Code.USD, 1.0, LocalDate.now(), true);
        when(alertRepository.existsById(1L)).thenReturn(true);
        when(alertRepository.findById(1L)).thenReturn(Optional.of(alert));
        //When
        Alert result = alertService.getAlert(1L);
        //Then
        assertEquals(alert, result);
    }

    @Test
    void saveAlert() {
        //Given
        Alert alert = new Alert(1L, "test", Code.USD, 1.0, LocalDate.now(), true);
        when(alertRepository.save(any(Alert.class))).thenReturn(alert);
        //When
        Alert result = alertService.saveAlert(alert);
        //Then
        assertEquals(alert, result);
    }

    @Test
    void saveAlertList() {
        //Given
        Alert alert = new Alert(1L, "test", Code.USD, 1.0, LocalDate.now(), true);
        List<Alert> list = List.of(alert);
        when(alertRepository.save(any(Alert.class))).thenReturn(alert);
        //When
        List<Alert> result = alertService.saveAlertList(list);
        //Then
        assertEquals(list, result);
    }
    @Test
    void updateAlert() throws AlertNotFoundException {
        //Given
        Alert alert = new Alert(1L, "test", Code.USD, 1.0, LocalDate.now(), true);
        when(alertRepository.save(any(Alert.class))).thenReturn(alert);
        when(alertRepository.existsById(1L)).thenReturn(true);
        //When
        Alert result = alertService.updateAlert(alert);
        //Then
        assertEquals(alert, result);
    }

    @Test
    void updateAlertList() throws AlertNotFoundException {
        //Given
        Alert alert = new Alert(1L, "test", Code.USD, 1.0, LocalDate.now(), true);
        List<Alert> list = List.of(alert);
        when(alertRepository.save(any(Alert.class))).thenReturn(alert);
        //When
        List<Alert> result = alertService.updateAlertList(list);
        //Then
        assertEquals(list, result);
    }

    @Test
    void deleteAlert() throws AlertNotFoundException {
        //Given
        when(alertRepository.existsById(1L)).thenReturn(true);
        //When
        alertService.deleteAlert(1L);
        //Then
        verify(alertRepository, times(1)).deleteById(1L);
    }
}
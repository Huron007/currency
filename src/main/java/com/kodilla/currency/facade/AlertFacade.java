package com.kodilla.currency.facade;

import com.kodilla.currency.dto.AlertDto;
import com.kodilla.currency.exception.AlertNotFoundException;
import com.kodilla.currency.mapper.AlertMapper;
import com.kodilla.currency.service.AlertService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AlertFacade {

    private final AlertService alertService;
    private final AlertMapper alertMapper;

    public List<AlertDto> getAllAlerts(){
        return alertMapper.mapToAlertDtoList(alertService.getAllAlerts());
    }

    public AlertDto getSingleAlert(final Long alertId) throws AlertNotFoundException {
        return alertMapper.mapToAlertDto(alertService.getAlert(alertId));
    }

    public AlertDto saveSingleAlert(final AlertDto alertDto) {
        return alertMapper.mapToAlertDto(alertService.saveAlert(alertMapper.mapToAlert(alertDto)));
    }

    public List<AlertDto> saveAlertList(final List<AlertDto> alertDtoList){
        return alertMapper.mapToAlertDtoList(alertService.saveAlertList(alertMapper.mapToAlertList(alertDtoList)));
    }

    public AlertDto updateSingleAlert(final AlertDto alertDto) throws AlertNotFoundException {
        return alertMapper.mapToAlertDto(alertService.updateAlert(alertMapper.mapToAlert(alertDto)));
    }

    public List<AlertDto> updateAlertList(final List<AlertDto> alertDtoList) throws AlertNotFoundException {
        return alertMapper.mapToAlertDtoList(alertService.updateAlertList(alertMapper.mapToAlertList(alertDtoList)));
    }

    public void deleteAlert(final Long alertId) throws AlertNotFoundException {
        alertService.deleteAlert(alertId);
    }
}

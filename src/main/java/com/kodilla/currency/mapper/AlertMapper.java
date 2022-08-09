package com.kodilla.currency.mapper;

import com.kodilla.currency.dto.AlertDto;
import com.kodilla.currency.entity.Alert;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AlertMapper {

    public Alert mapToAlert(AlertDto alertDto){
        if(alertDto.getId() == null){
            return Alert.builder()
                    .id(0L)
                    .name(alertDto.getName())
                    .code(alertDto.getCode())
                    .trackedMargin(alertDto.getTrackedMargin())
                    .creationDate(alertDto.getCreationDate())
                    .active(alertDto.isActive())
                    .build();
        } else {
            return Alert.builder()
                    .id(alertDto.getId())
                    .name(alertDto.getName())
                    .code(alertDto.getCode())
                    .trackedMargin(alertDto.getTrackedMargin())
                    .creationDate(alertDto.getCreationDate())
                    .active(alertDto.isActive())
                    .build();
        }
    }

    public AlertDto mapToAlertDto(Alert alert){
        return new AlertDto(
                alert.getId(),
                alert.getName(),
                alert.getCode(),
                alert.getTrackedMargin(),
                alert.getCreationDate(),
                alert.isActive()
        );
    }

    public List<Alert> mapToAlertList(List<AlertDto> alertDtos){
        return alertDtos.stream()
                .map(this::mapToAlert)
                .collect(Collectors.toList());
    }

    public List<AlertDto> mapToAlertDtoList(List<Alert> alerts){
        return alerts.stream()
                .map(this::mapToAlertDto)
                .collect(Collectors.toList());
    }
}

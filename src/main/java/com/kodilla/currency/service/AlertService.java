package com.kodilla.currency.service;

import com.kodilla.currency.entity.Alert;
import com.kodilla.currency.exception.AlertNotFoundException;
import com.kodilla.currency.repository.AlertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlertService {

    private final AlertRepository alertRepository;

    public List<Alert> getAllAlerts(){
        return alertRepository.findAll();
    }

    public Alert getAlert(final Long alertId) throws AlertNotFoundException {
        if(alertRepository.existsById(alertId)){
            if(alertRepository.findById(alertId).isPresent()){
                return alertRepository.findById(alertId).get();
            } else throw new AlertNotFoundException();
        } else throw new AlertNotFoundException();
    }

    public Alert saveAlert(final Alert alert) {
        return alertRepository.save(alert);
    }

    public List<Alert> saveAlertList(final List<Alert> alerts) {
        return alerts.stream()
                .map(alertRepository::save)
                .collect(Collectors.toList());
    }

    public Alert updateAlert(final Alert alert) throws AlertNotFoundException {
        if(alertRepository.existsById(alert.getId())){
            return alertRepository.save(alert);
        } else throw new AlertNotFoundException();
    }

    public List<Alert> updateAlertList(final List<Alert> alerts) throws AlertNotFoundException {
        if(alerts.stream().map(e -> alertRepository.findById(e.getId()).isPresent()).count() == alerts.size()){
            return alerts.stream()
                    .map(alertRepository::save)
                    .collect(Collectors.toList());
        } else throw new AlertNotFoundException();
    }

    public void deleteAlert(final Long alertId) throws AlertNotFoundException {
        if(alertRepository.existsById(alertId)){
            alertRepository.deleteById(alertId);
        } else throw new AlertNotFoundException();
    }
}

package com.kodilla.currency.controller;

import com.kodilla.currency.dto.AlertDto;
import com.kodilla.currency.exception.AlertNotFoundException;
import com.kodilla.currency.facade.AlertFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/v1/alert")
public class AlertController {

    private final AlertFacade alertFacade;

    @GetMapping
    public ResponseEntity<List<AlertDto>> getAlerts(){
        return ResponseEntity.ok(alertFacade.getAllAlerts());
    }

    @GetMapping(value = "{alertId}")
    public ResponseEntity<AlertDto> getAlert(@PathVariable Long alertId) throws AlertNotFoundException {
        return ResponseEntity.ok(alertFacade.getSingleAlert(alertId));
    }

    @DeleteMapping(value = "{alertId}")
    public ResponseEntity<Void> deleteAlert(@PathVariable Long alertId) throws AlertNotFoundException {
        alertFacade.deleteAlert(alertId);
        return ResponseEntity.ok().build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AlertDto> createAlert(@RequestBody AlertDto alertDto) {
        return ResponseEntity.ok(alertFacade.saveSingleAlert(alertDto));
    }

    @PutMapping(value = "AlertList",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AlertDto>> createAlertList(@RequestBody List<AlertDto> alertDtoList) {
        return ResponseEntity.ok(alertFacade.saveAlertList(alertDtoList));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AlertDto> updateAlert(@RequestBody AlertDto alertDto) throws AlertNotFoundException {
        return ResponseEntity.ok(alertFacade.updateSingleAlert(alertDto));
    }

    @PostMapping(value = "AlertList", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AlertDto>> updateAlertList(@RequestBody List<AlertDto> alertDtoList) throws AlertNotFoundException {
        return ResponseEntity.ok(alertFacade.updateAlertList(alertDtoList));
    }
}

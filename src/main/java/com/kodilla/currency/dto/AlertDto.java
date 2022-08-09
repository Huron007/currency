package com.kodilla.currency.dto;

import com.kodilla.currency.entity.Code;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlertDto {
    private Long id;
    private String name;
    private Code code;
    private Double trackedMargin;
    private LocalDate creationDate;
    private boolean active;
}

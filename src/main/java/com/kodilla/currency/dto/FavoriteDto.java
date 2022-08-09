package com.kodilla.currency.dto;

import com.kodilla.currency.entity.Code;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteDto {
    private Long id;
    private String name;
    private Code code;
}

package com.kodilla.currency.entity;

import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
@NamedQuery(
        name = "Currency.checkDuplicates",
        query = "FROM Currency WHERE code = :CODE AND effectiveDate = :DATE"
)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class Currency {

    @Id
    @GeneratedValue
    @Column(unique = true, nullable = false)
    private Long id;

    private String name;

    @Column(nullable = false, updatable = false)
    private Code code;

    @Column(nullable = false, updatable = false)
    private LocalDate effectiveDate;

    private Double exchangeRate;
}

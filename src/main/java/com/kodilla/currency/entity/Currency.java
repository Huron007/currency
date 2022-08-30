package com.kodilla.currency.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency currency = (Currency) o;
        return Objects.equals(id, currency.id) && Objects.equals(name, currency.name) && code == currency.code && Objects.equals(effectiveDate, currency.effectiveDate) && Objects.equals(exchangeRate, currency.exchangeRate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, code, effectiveDate, exchangeRate);
    }
}

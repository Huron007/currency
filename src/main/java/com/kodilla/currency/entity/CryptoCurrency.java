package com.kodilla.currency.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@NamedQuery(
        name = "CryptoCurrency.checkDuplicates",
        query = "FROM CryptoCurrency WHERE name = :NAME AND effectiveDate = :DATE"
)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class CryptoCurrency {

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
        CryptoCurrency that = (CryptoCurrency) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && code == that.code && Objects.equals(effectiveDate, that.effectiveDate) && Objects.equals(exchangeRate, that.exchangeRate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, code, effectiveDate, exchangeRate);
    }
}

package com.kodilla.currency.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@NamedQuery(
        name = "Favorite.findDuplicates",
        query = "FROM Favorite WHERE name = :NAME AND code = :CODE"
)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class Favorite {

    @Id
    @GeneratedValue
    @Column(unique = true, updatable = false)
    private Long id;

    @Column(unique = true)
    private String name;

    @Column(unique = true)
    private Code code;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Favorite favorite = (Favorite) o;
        return Objects.equals(id, favorite.id) && Objects.equals(name, favorite.name) && code == favorite.code;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, code);
    }
}

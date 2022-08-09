package com.kodilla.currency.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
}

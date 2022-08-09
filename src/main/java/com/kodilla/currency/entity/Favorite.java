package com.kodilla.currency.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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

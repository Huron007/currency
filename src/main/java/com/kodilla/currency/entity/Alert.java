package com.kodilla.currency.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class Alert {

    @Id
    @GeneratedValue
    @Column(unique = true, nullable = false)
    private Long id;

    private String name;

    private Code code;

    private Double trackedMargin;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDate creationDate;

    private boolean active;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Alert alert = (Alert) o;
        return active == alert.active && Objects.equals(id, alert.id) && Objects.equals(name, alert.name) && code == alert.code && Objects.equals(trackedMargin, alert.trackedMargin) && Objects.equals(creationDate, alert.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, code, trackedMargin, creationDate, active);
    }
}

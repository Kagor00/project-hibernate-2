package com.javarush.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(schema = "movie", name = "inventory")
@Getter
@Setter
@ToString
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_id", columnDefinition = "mediumint")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "film_id", referencedColumnName = "film_id")
    private Film film;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "store_id", referencedColumnName = "store_id")
    private Store store;

    @Column(name = "last_update")
    @UpdateTimestamp
    private LocalDateTime lastUpdate;
}

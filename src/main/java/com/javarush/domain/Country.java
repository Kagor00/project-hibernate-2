package com.javarush.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(schema = "movie", name = "country")
@Getter
@Setter
@ToString
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id")
    private Short id;

    private String country;

    @Column(name = "last_update")
    @UpdateTimestamp
    private LocalDateTime lastUpdate;
}

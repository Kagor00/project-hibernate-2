package com.javarush.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Entity
@Table(schema = "movie", name = "film_text")
@Getter
@Setter
@ToString
public class FilmText implements Serializable {

    @Id
    @OneToOne(cascade = CascadeType.PERSIST)
    @MapsId
    @JoinColumn(name = "film_id", referencedColumnName = "film_id")
    private Film film;

    private String title;

    @Column(columnDefinition = "text")
    private String description;
}

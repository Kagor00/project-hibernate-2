package com.javarush.management;

import com.javarush.domain.Feature;
import com.javarush.domain.Film;
import com.javarush.util.MovieSessionFactory;
import org.hibernate.Session;

import java.util.Set;

public class Runner {
    public static void main(String[] args) {
        try (Session session = MovieSessionFactory.getSessionFactory().openSession()) {
            Film film = session.load(Film.class, (short) 19);
            System.out.println(film.getSpecialFeature());
        }
    }
}

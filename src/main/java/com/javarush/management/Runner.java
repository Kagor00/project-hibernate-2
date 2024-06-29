package com.javarush.management;

import com.javarush.domain.Film;
import com.javarush.domain.FilmText;
import com.javarush.util.MovieSessionFactory;
import org.hibernate.Session;

public class Runner {
    public static void main(String[] args) {
        try (Session session = MovieSessionFactory.getSessionFactory().openSession()) {

        }
    }
}

package com.javarush.management;

import com.javarush.domain.Film;
import com.javarush.service.MovieService;
import com.javarush.util.MovieSessionFactory;
import org.hibernate.SessionFactory;

public class Runner {
    public static void main(String[] args) {
        SessionFactory sessionFactory = MovieSessionFactory.getSessionFactory();
        MovieService movieService = new MovieService(sessionFactory);
        Film film = movieService.getFirstFilm();
        System.out.println(film);
    }
}
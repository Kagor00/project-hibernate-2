package com.javarush.repository.dao;

import com.javarush.domain.Film;
import org.hibernate.SessionFactory;

public class FilmTextDAO extends AbstractHibernateDAO<Film> {
    public FilmTextDAO(SessionFactory sessionFactory) {
        super(Film.class, sessionFactory);
    }
}

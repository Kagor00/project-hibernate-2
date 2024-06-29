package com.javarush.repository;

import com.javarush.domain.*;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Properties;

public class MovieManager {
    private final SessionFactory sessionFactory;

    public MovieManager() {
        Properties properties = new Properties();
        properties.put(Environment.DRIVER, "com.p6spy.engine.spy.P6SpyDriver");
        properties.put(Environment.URL, "jdbc:p6spy:mysql://localhost:3306/movie");
        properties.put(Environment.USER, "root");
        properties.put(Environment.PASS, "root");
        properties.put(Environment.SHOW_SQL, "true");
        properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        properties.put(Environment.HBM2DDL_AUTO, "validate");

        sessionFactory = new Configuration()
                .setProperties(properties)
                .addAnnotatedClass(Film.class)
                .addAnnotatedClass(Language.class)
                .addAnnotatedClass(Actor.class)
                .addAnnotatedClass(Category.class)
                .addAnnotatedClass(Inventory.class)
                .addAnnotatedClass(Store.class)
                .addAnnotatedClass(Staff.class)
                .addAnnotatedClass(Address.class)
                .addAnnotatedClass(City.class)
                .addAnnotatedClass(Country.class)
                .addAnnotatedClass(Rental.class)
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Payment.class)
                .buildSessionFactory();
    }

    public Film getFilmById(Short id) {
        try (Session session = sessionFactory.openSession()) {
            Film film = session.get(Film.class, id);
            Hibernate.initialize(film.getActors());
            Hibernate.initialize(film.getCategories());
            return film;
        }
    }

    public List<Actor> getActorsByFilm(Film film) {
        try (Session session = sessionFactory.openSession()) {
            String sqlQuery = "SELECT a.* " +
                    "FROM actor a " +
                    "JOIN film_actor fa ON a.actor_id = fa.actor_id " +
                    "WHERE fa.film_id = :filmId";

            return session.createNativeQuery(sqlQuery, Actor.class)
                    .setParameter("filmId", film.getId())
                    .getResultList();
        }
    }

    public Actor getActorById(Short id) {
        try (Session session = sessionFactory.openSession()) {
            Actor actor = session.get(Actor.class, id);
            Hibernate.initialize(actor.getFilms());
            return actor;
        }
    }

    public Category getCategoryById(Byte id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Category.class, id);
        }
    }

    public void saveFilm(Film film) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(film);
            transaction.commit();
            System.out.println(film.toString() + "saved");
        }
    }

    public Inventory getInventoryById(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Inventory.class, id);
        }
    }

    public Address getAddressById(Short id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Address.class, id);
        }
    }

    public void saveAddress(Address address) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(address);
            transaction.commit();
            System.out.println(address.toString() + " saved successful.");
        }
    }

    public void deleteAddressById(Short id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Address address = session.get(Address.class, id);
            session.remove(address);
            transaction.commit();
            System.out.println("Address by id = " + id + " deleted successful.");
        }
    }

    public void deleteCityAndCountryByName(String city, String country) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Query<City> cityQuery = session.createQuery("delete from City where city =: city");
            cityQuery.setParameter("city", city);
            cityQuery.executeUpdate();
            session.flush();
            Query<Country> countryQuery = session.createQuery("delete from Country where country =: country");
            countryQuery.setParameter("country", country);
            countryQuery.executeUpdate();
            session.flush();
            transaction.commit();
            System.out.println("Cities And Countries deleted successful.");
        }
    }

    public Rental getRentalById(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Rental.class, id);
        }
    }

    public Payment getPaymentById(Short id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Payment.class, id);
        }
    }
}

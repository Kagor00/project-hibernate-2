package com.javarush.dao;

import com.javarush.domain.Country;
import org.hibernate.SessionFactory;

public class CountryDAO extends AbstractHibernateDAO<Country> {
    public CountryDAO(SessionFactory sessionFactory) {
        super(Country.class, sessionFactory);
    }
}

package com.javarush.repository.dao;

import com.javarush.domain.City;
import jakarta.persistence.TypedQuery;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class CityDAO extends AbstractHibernateDAO<City> {
    public CityDAO(SessionFactory sessionFactory) {
        super(City.class, sessionFactory);
    }

    public City getByName(String cityName) {
        Query<City> query = getCurrentSession().createQuery("select c from City c where c.city=: city", City.class);
        query.setParameter("city", cityName);
        query.setMaxResults(1);
        return query.getSingleResult();
    }
}

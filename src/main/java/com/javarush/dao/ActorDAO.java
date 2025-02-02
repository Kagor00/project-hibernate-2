package com.javarush.dao;

import com.javarush.domain.Actor;
import org.hibernate.SessionFactory;

public class ActorDAO extends AbstractHibernateDAO<Actor> {
    public ActorDAO(SessionFactory sessionFactory) {
        super(Actor.class, sessionFactory);
    }
}

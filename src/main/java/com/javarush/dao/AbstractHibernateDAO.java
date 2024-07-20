package com.javarush.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public abstract class AbstractHibernateDAO<T> {
    private final Class<T> clazz;
    private final SessionFactory sessionFactory;

    public AbstractHibernateDAO(Class<T> clazz, SessionFactory sessionFactory) {
        this.clazz = clazz;
        this.sessionFactory = sessionFactory;
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public T getById(final Number id) {
        return getCurrentSession().get(clazz, id);
    }

    public List<T> getItems(int offset, int count) {
        Query<T> query = getCurrentSession().createQuery("from " + clazz.getName(), clazz);
        query.setFirstResult(offset);
        query.setMaxResults(count);
        return query.getResultList();
    }

    public List<T> findAll() {
        return getCurrentSession().createQuery("from " + clazz.getName(), clazz).list();
    }

    public T save(final T entity) {
        getCurrentSession().saveOrUpdate(entity);
        return entity;
    }

    public T update(final T entity) {
        return (T) getCurrentSession().merge(entity);
    }

    public void delete(final T entity) {
       getCurrentSession().delete(entity);
    }

    public void deleteById(final Number entityId) {
        T entity = getById(entityId);
        delete(entity);
    }
}

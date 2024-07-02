package com.javarush.repository.dao;

import com.javarush.domain.Category;
import org.hibernate.SessionFactory;

public class CategoryDAO extends AbstractHibernateDAO<Category> {
    public CategoryDAO(SessionFactory sessionFactory) {
        super(Category.class, sessionFactory);
    }
}

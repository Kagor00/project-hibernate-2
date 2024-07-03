package com.javarush.dao;

import com.javarush.domain.Language;
import org.hibernate.SessionFactory;

public class LanguageDAO extends AbstractHibernateDAO<Language> {
    public LanguageDAO(SessionFactory sessionFactory) {
        super(Language.class, sessionFactory);
    }
}

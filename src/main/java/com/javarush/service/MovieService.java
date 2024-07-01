package com.javarush.service;

import com.javarush.dao.*;
import com.javarush.domain.Film;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import static org.hibernate.resource.transaction.spi.TransactionStatus.MARKED_ROLLBACK;

public class MovieService {
    private final SessionFactory sessionFactory;
    private final ActorDAO actorDAO;
    private final AddressDAO addressDAO;
    private final CategoryDAO categoryDAO;
    private final CityDAO cityDAO;
    private final CountryDAO countryDAO;
    private final CustomerDAO customerDAO;
    private final FilmDAO filmDAO;
    private final FilmTextDAO filmTextDAO;
    private final InventoryDAO inventoryDAO;
    private final LanguageDAO languageDAO;
    private final PaymentDAO paymentDAO;
    private final RentalDAO rentalDAO;
    private final StaffDAO staffDAO;
    private final StoreDAO storeDAO;


    public MovieService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        actorDAO = new ActorDAO(sessionFactory);
        addressDAO = new AddressDAO(sessionFactory);
        categoryDAO = new CategoryDAO(sessionFactory);
        cityDAO = new CityDAO(sessionFactory);
        countryDAO = new CountryDAO(sessionFactory);
        customerDAO = new CustomerDAO(sessionFactory);
        filmDAO = new FilmDAO(sessionFactory);
        filmTextDAO = new FilmTextDAO(sessionFactory);
        inventoryDAO = new InventoryDAO(sessionFactory);
        languageDAO = new LanguageDAO(sessionFactory);
        paymentDAO = new PaymentDAO(sessionFactory);
        rentalDAO = new RentalDAO(sessionFactory);
        staffDAO = new StaffDAO(sessionFactory);
        storeDAO = new StoreDAO(sessionFactory);

    }

    public Film getFirstFilm() {
        Transaction transaction = null;
        Film film = null;
        try {
            transaction = sessionFactory.getCurrentSession().beginTransaction();
            film = filmDAO.getById((short) 1);
            transaction.commit();
        } catch (Exception e) {
            if (transaction == null &&
                    (transaction.isActive() || transaction.getStatus() == MARKED_ROLLBACK)) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return film;
    }

    public void createCustomer() {

    }
}

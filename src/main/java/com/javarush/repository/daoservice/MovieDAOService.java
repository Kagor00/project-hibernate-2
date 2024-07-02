package com.javarush.repository.daoservice;

import com.javarush.repository.dao.*;
import lombok.Getter;
import org.hibernate.SessionFactory;

@Getter
public class MovieDAOService {
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


    public MovieDAOService(SessionFactory sessionFactory) {
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

//    public void createCustomer(Customer customer) {
//        Transaction transaction = null;
//
//        try {
//            transaction = sessionFactory.getCurrentSession().beginTransaction();
//            customerDAO.save(customer);
//            transaction.commit();
//            System.out.println("Customer " + customer.getFirstName() + " " + customer.getLastName() + " added successfully.");
//        } catch (Exception e) {
//            if (transaction != null &&
//                    (transaction.isActive() || transaction.getStatus() == MARKED_ROLLBACK)) {
//                transaction.rollback();
//            }
//            e.printStackTrace();
//        }
//    }
}

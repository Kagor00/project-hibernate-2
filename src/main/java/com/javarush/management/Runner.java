package com.javarush.management;

import com.javarush.domain.Address;
import com.javarush.domain.City;
import com.javarush.domain.Customer;
import com.javarush.domain.Store;
import com.javarush.repository.dao.CustomerDAO;
import com.javarush.repository.daoservice.MovieDAOService;
import com.javarush.util.MovieSessionFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.time.LocalDateTime;

import static org.hibernate.resource.transaction.spi.TransactionStatus.MARKED_ROLLBACK;

public class Runner {
    private final SessionFactory sessionFactory = MovieSessionFactory.getSessionFactory();
    private final MovieDAOService movieDAOManager = new MovieDAOService(sessionFactory);

    public static void main(String[] args) {
        Runner runner = new Runner();

    }

    private Customer createCustomer() {
        Customer customer = null;
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            customer = new Customer();
            Store store = movieDAOManager.getStoreDAO().getItems(0, 1).get(0);
            Address address = new Address();
            address.setAddress("Test Address");
            address.setDistrict("Test District");
            address.setPostalCode("00000");
            address.setPhone("+100000000000");
            City city = movieDAOManager.getCityDAO().getByName("La Paz");
            address.setCity(city);
            movieDAOManager.getAddressDAO().save(address);
            customer.setStore(store);
            customer.setAddress(address);
            customer.setCreateDate(LocalDateTime.now());
            customer.setEmail("test@email.com");
            customer.setFirstName("FirstName");
            customer.setLastName("LastName");
            customer.setActive(true);
            movieDAOManager.getCustomerDAO().save(customer);
            transaction.commit();
            System.out.println(customer.getFirstName() + " " + customer.getLastName() + " created successfully.");
        } catch (Exception e) {
            if (transaction == null &&
                    (transaction.isActive()) || transaction.getStatus() == MARKED_ROLLBACK) {
                transaction.rollback();
            }

            e.printStackTrace();
        }

        return customer;
    }
}
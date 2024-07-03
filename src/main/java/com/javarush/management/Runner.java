package com.javarush.management;

import com.javarush.dao.DAOFacade;
import com.javarush.domain.*;
import com.javarush.util.MovieSessionFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Runner {
    private final SessionFactory sessionFactory = MovieSessionFactory.getSessionFactory();
    private final DAOFacade daoFacade = new DAOFacade(sessionFactory);

    public static void main(String[] args) {
        Runner runner = new Runner();
        Customer customer = runner.createCustomer();
        runner.customerReturnInventory();
        runner.customerRentInventory(customer);
        runner.addNewFilmAvailableForRent();
    }

    private Customer createCustomer() {
        Customer customer = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            customer = new Customer();
            Store store = daoFacade.getStoreDAO().getItems(0, 1).get(0);
            Address address = new Address();
            address.setAddress("Test Address");
            address.setDistrict("Test District");
            address.setPostalCode("00000");
            address.setPhone("+100000000000");
            City city = daoFacade.getCityDAO().getByName("La Paz");
            address.setCity(city);
            daoFacade.getAddressDAO().save(address);
            customer.setStore(store);
            customer.setAddress(address);
            customer.setCreateDate(LocalDateTime.now());
            customer.setEmail("test@email.com");
            customer.setFirstName("FirstName");
            customer.setLastName("LastName");
            customer.setActive(true);
            daoFacade.getCustomerDAO().save(customer);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return customer;
    }

    private void customerReturnInventory() {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            Rental rental = daoFacade.getRentalDAO().getAnyUnreturnedRental();
            rental.setReturnDate(LocalDateTime.now());
            daoFacade.getRentalDAO().save(rental);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void customerRentInventory(Customer customer) {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();

            Film film = daoFacade.getFilmDAO().getFirstAvailableFilmForRent();

            Inventory inventory = new Inventory();
            Store store = daoFacade.getStoreDAO().getItems(0, 1).get(0);
            inventory.setStore(store);
            inventory.setFilm(film);
            daoFacade.getInventoryDAO().save(inventory);

            Staff staff = store.getStaff();

            Rental rental = new Rental();
            rental.setCustomer(customer);
            rental.setInventory(inventory);
            rental.setStaff(staff);
            rental.setRentalDate(LocalDateTime.now());
            daoFacade.getRentalDAO().save(rental);

            Payment payment = new Payment();
            payment.setCustomer(customer);
            payment.setStaff(staff);
            payment.setRental(rental);
            payment.setPaymentDate(LocalDateTime.now());
            payment.setAmount(BigDecimal.valueOf(88.55));

            daoFacade.getPaymentDAO().save(payment);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addNewFilmAvailableForRent() {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            Film film = new Film();
            Language language = daoFacade.getLanguageDAO().getItems(0, 1).get(0);
            Set<Actor> actors = new HashSet<>(daoFacade.getActorDAO().getItems(0, 5));
            Set<Category> categories = new HashSet<>(daoFacade.getCategoryDAO().getItems(0, 6));
            film.setRating(Rating.R);
            film.setActors(actors);
            film.setCategories(categories);
            film.setLanguage(language);
            film.setReleaseYear(2013);
            film.setDescription("Comedy, Drama");
            film.setOriginalLanguage(language);
            film.setTitle("Wolf Of Wall Street");
            film.setSpecialFeatures(Set.of(Feature.TRAILERS, Feature.COMMENTARIES, Feature.BEHIND_THE_SCENES));
            film.setLength((short) 180);
            film.setRentalRate(BigDecimal.valueOf(4.99));
            film.setRentalDuration((byte) 6);
            film.setReplacementCost(BigDecimal.valueOf(22.99));

            FilmText filmText = new FilmText();
            filmText.setFilm(film);
            filmText.setTitle("Wolf of wall street");
            filmText.setDescription("desc");
            film.setFilmText(filmText);
            daoFacade.getFilmDAO().save(film);
            daoFacade.getFilmTextDAO().save(filmText);


            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
package org.example.Services;
import lombok.Getter;
import org.example.Entities.House;
import org.example.Entities.Street;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Hibernate realization for database
 */
@Getter
public class HibernateInterfaceRealization implements DatabaseInterface{
    Configuration config = new Configuration();
    SessionFactory sessionFactory;

    /**
     * creates connection
     * @return Session
     */
    private Session connection(){
        Session session = sessionFactory.openSession();

        return session;
    }

    /**
     * Constructor
     */
    public HibernateInterfaceRealization(){
        config.configure("hibernate.cfg.xml");
        this.sessionFactory = config.buildSessionFactory();
        Session session = connection();
        session.close();
    }

    /**
     * saves Street
     * @param street
     * @return Street
     */
    public Street saveStreet(Street street) {
        Session session = connection();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(street);
            transaction.commit();
            session.close();
            return street;
        } catch (RuntimeException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    /**
     * Saves house
     * @param house
     * @return House
     */
    public House saveHouse(House house) {
        Session session = connection();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(house);
            transaction.commit();
            session.close();
            return house;
        } catch (RuntimeException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    /**
     * delete house by id
     * @param id
     */
    public void deleteHouseById(int id) {
        Session session = connection();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            House house = session.load(House.class, id);
            session.delete(house);
            transaction.commit();
            session.close();
        } catch (RuntimeException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    /**
     * delete street by id
     * @param id
     */
    public void deleteStreetById(int id) {
        Session session = connection();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Street street = session.load(Street.class, id);
            session.delete(street);
            transaction.commit();
            session.close();
        } catch (RuntimeException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    /**
     * delete street by entity
     * @param street
     */
    public void deleteStreetByEntity(Street street) {
        Session session = connection();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(street);
            transaction.commit();
            session.close();
        } catch (RuntimeException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    /**
     * delete house by entity
     * @param house
     */
    public void deleteHouseByEntity(House house) {
        Session session = connection();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(house);
            transaction.commit();
            session.close();
        } catch (RuntimeException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    /**
     * deletes all houses
     * @throws SQLException
     */
    public void deleteAllHouses() throws SQLException{
        List<House> houses = getAllHouses();
        houses.forEach(house -> deleteHouseByEntity(house));
    }

    /**
     * deletes all streets
     * @throws SQLException
     */
    public void deleteAllStreets() throws SQLException{
        List<Street> streets = getAllStreets();
        streets.forEach(street -> deleteStreetByEntity(street));
    }

    /**
     * updates house
     * @param house
     * @return House
     */
    public House updateHouse(House house){
        Session session = connection();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(house);
            transaction.commit();
            session.close();
            return house;
        } catch (RuntimeException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    /**
     * updates Street
     * @param street
     * @return Street
     */
    public Street updateStreet(Street street){
        Session session = connection();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(street);
            transaction.commit();
            session.close();
            return street;
        } catch (RuntimeException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    /**
     * get house by id
     * @param id
     * @return House
     * @throws SQLException
     */
    public House getHouseById(int id) throws SQLException{
        Session session = connection();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            House house = session.get(House.class, id);
            transaction.commit();
            session.close();
            return house;
        } catch (RuntimeException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    /**
     * gets street by id
     * @param id
     * @return Street
     * @throws SQLException
     */
    public Street getStreetById(int id) throws SQLException{
        Session session = connection();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Street street = session.get(Street.class, id);
            transaction.commit();
            session.close();
            return street;
        } catch (RuntimeException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    /**
     * @return List<House>
     * @throws SQLException
     */
    public List<House> getAllHouses() throws SQLException{
        Session session = connection();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<House> criteria = builder.createQuery(House.class);
        criteria.from(House.class);
        List<House> houses = session.createQuery(criteria).getResultList();
        session.close();
        return houses;
    }

    /**
     * @return List<Street>
     * @throws SQLException
     */
    public List<Street> getAllStreets() throws SQLException{
        Session session = connection();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Street> criteria = builder.createQuery(Street.class);
        criteria.from(Street.class);
        List<Street> streets = session.createQuery(criteria).getResultList();
        session.close();
        return streets;
    }

    /**
     * @param parentId
     * @return List House
     * @throws SQLException
     */
    public List<House> getAllByVId(int parentId) throws SQLException{
        List<House> allHouses = getAllHouses();
        List<House> correctHouses = new ArrayList<>();
        for (House x : allHouses) {
            if (x.getStreetNameId().getId() == parentId)
                correctHouses.add(x);
        }

        return correctHouses;
    }

}

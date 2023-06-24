package org.example;

import org.example.Entities.ConstructionTypes;
import org.example.Entities.House;
import org.example.Entities.Street;
import org.example.Services.HibernateInterfaceRealization;
import org.example.Services.JDBSInterfaceRealization;
import org.example.Services.MyBatisInterfaseRealization;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Date;
import java.util.List;

public class DatabaseTests {
    /**
     * @throws SQLException
     */
    @Test
    public void JDBSTest() throws SQLException {
        String url = "jdbc:h2:mem:lab2;INIT=RUNSCRIPT FROM 'classpath:database_preparation.sql';";
        String user = "";
        String password = "";
        JDBSInterfaceRealization database = new JDBSInterfaceRealization(url, user, password);
        Date dateOfConstruction = new Date(System.currentTimeMillis());
        ConstructionTypes constructionType = ConstructionTypes.Garage;
        Street street = new Street(2233333, "Street", 12345);
        House house = new House(2233333, "house", dateOfConstruction, 6, constructionType, street);
        House housee = new House(2233334, "housee", dateOfConstruction, 6, constructionType, street);
        database.saveStreet(street);
        database.saveHouse(house);
        database.saveHouse(housee);
        House hhouse = database.getHouseById(2233333);
        Street streett = database.getStreetById(2233333);
        List<House> houses = database.getAllHouses();
        List<Street> streets = database.getAllStreets();
        List<House> housesVid = database.getAllByVId(2233333);

        Assertions.assertTrue(houses.size() == 2 && housesVid.size() == 2 && streets.size() == 1, "Error: Not all elements were added to database");

        house = new House(2233333, "new house", dateOfConstruction, 6, constructionType, street);
        street = new Street(2233333, "new street", 12345);

        database.updateHouse(house);
        database.updateStreet(street);

        hhouse = database.getHouseById(2233333);
        streett = database.getStreetById(2233333);

        Assertions.assertTrue(hhouse.getHouseName() == house.getHouseName() && streett.getStreetName() == street.getStreetName(), "Error: entities in db were not updated");

        database.deleteAllHouses();
        database.deleteAllStreets();
}

    /**
     * @throws IOException
     * @throws SQLException
     */
    @Test
    public void MyBatisTest() throws IOException, SQLException {
        MyBatisInterfaseRealization myBatisInterfaseRealization = new MyBatisInterfaseRealization();
        Date dateOfConstruction = new Date(System.currentTimeMillis());
        ConstructionTypes constructionType = ConstructionTypes.Garage;
        Street street = new Street(2233333, "newstreet",213);
        House house = new House(2233333, "house", dateOfConstruction, 6, constructionType, street);
        House housee = new House(2233334, "housee", dateOfConstruction, 6, constructionType, street);
        myBatisInterfaseRealization.saveStreet(street);
        myBatisInterfaseRealization.saveHouse(house);
        myBatisInterfaseRealization.saveHouse(housee);

        List<House> houses = myBatisInterfaseRealization.getAllHouses();
        List<Street> streets = myBatisInterfaseRealization.getAllStreets();
        List<House> housesVid = myBatisInterfaseRealization.getAllByVId(2233333);

        Assertions.assertTrue(houses.size() == 2 && housesVid.size() == 2 && streets.size() == 1, "Error: Not all elements were added to database");

        house = new House(2233333, "new house", dateOfConstruction, 6, constructionType, street);
        street = new Street(2233333, "new street", 12345);

        myBatisInterfaseRealization.updateHouse(house);
        myBatisInterfaseRealization.updateStreet(street);

        House hhouse = myBatisInterfaseRealization.getHouseById(2233333);
        Street streett = myBatisInterfaseRealization.getStreetById(2233333);

        Assertions.assertTrue(hhouse.getHouseName() == house.getHouseName() && streett.getStreetName() == street.getStreetName(), "Error: entities in db were not updated");

        myBatisInterfaseRealization.deleteAllHouses();
        myBatisInterfaseRealization.deleteAllStreets();
    }

    /**
     * @throws SQLException
     */
    @Test
    public void HibernateTest() throws SQLException {
        HibernateInterfaceRealization hibernate = new HibernateInterfaceRealization();
        Date dateOfConstruction = new Date(System.currentTimeMillis());
        ConstructionTypes constructionType = ConstructionTypes.Garage;
        Street street = new Street(2233333, "newstreet",213);
        House house = new House(2233333, "house", dateOfConstruction, 6, constructionType, street);
        House housee = new House(2233334, "housee", dateOfConstruction, 6, constructionType, street);
        hibernate.saveStreet(street);
        hibernate.saveHouse(house);
        hibernate.saveHouse(housee);

        List<House> houses = hibernate.getAllHouses();
        List<Street> streets = hibernate.getAllStreets();
        List<House> housesByVid = hibernate.getAllByVId(2233333);

        Assertions.assertTrue(houses.size() == 2 && housesByVid.size() == 2 && streets.size() == 1, "Error: Not all elements were added to database");

        house = new House(2233333, "new house", dateOfConstruction, 6, constructionType, street);
        street = new Street(2233333, "new street", 12345);

        hibernate.updateHouse(house);
        hibernate.updateStreet(street);

        House hhouse = hibernate.getHouseById(2233333);
        Street streett = hibernate.getStreetById(2233333);

        Assertions.assertTrue(hhouse.getHouseName() == house.getHouseName() && streett.getStreetName() == street.getStreetName(), "Error: entities in db were not updated");

        hibernate.deleteAllHouses();
        hibernate.deleteAllStreets();
    }
}

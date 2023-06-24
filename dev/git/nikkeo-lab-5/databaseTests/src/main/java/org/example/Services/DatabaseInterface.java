package org.example.Services;

import org.example.Entities.House;
import org.example.Entities.Street;

import java.sql.SQLException;
import java.util.List;

public interface DatabaseInterface {
    public Street saveStreet(Street street);
    public House saveHouse(House house);
    public void deleteHouseById(int id) throws SQLException;
    public void deleteStreetById(int id) throws SQLException;

    public void deleteStreetByEntity(Street street) throws SQLException;

    public void deleteHouseByEntity(House house) throws SQLException;
    public void deleteAllHouses() throws SQLException;
    public void deleteAllStreets() throws SQLException;
    public House updateHouse(House house);

    public Street updateStreet(Street street);
    public House getHouseById(int id) throws SQLException;

    public Street getStreetById(int id) throws SQLException;
    public List<House> getAllHouses() throws SQLException;

    public List<Street> getAllStreets() throws SQLException;

    public List<House> getAllByVId(int parentId) throws SQLException;

}

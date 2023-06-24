package org.example.Services;
import org.example.Entities.ConstructionTypes;
import org.example.Entities.House;
import org.example.Entities.Street;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBSInterfaceRealization implements DatabaseInterface {
    private Connection connection;
    private String url;
    private String user;
    private String password;

    /**
     * @return Connection
     */
    private Connection OpenConnection(){
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new Error("Not connected", e);
        }
    }

    /**
     * @param url
     * @param username
     * @param password
     * @throws SQLException
     */
    public JDBSInterfaceRealization(String url, String username, String password) throws SQLException {
        this.url = url;
        this.user = username;
        this.password = password;
        this.connection = OpenConnection();
    }

    /**
     * @param street
     * @return Street
     */
    public Street saveStreet(Street street){
        String sql = "INSERT INTO Street (id, streetName, postcode) VALUES (?, ?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, street.getId());
            statement.setString(2, street.getStreetName());
            statement.setInt(3, street.getPostcode());

            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return street;
    }

    /**
     * @param house
     * @return House
     */
    public House saveHouse(House house){
        String sql = "INSERT INTO House (id, houseName, dateOfConstruction, quantityOfFloors, typeOfConstruction, streetNameId) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, house.getId());
            statement.setString(2, house.getHouseName());
            statement.setDate(3, house.getDateOfConstruction());
            statement.setInt(4, house.getQuantityOfFloors());
            statement.setString(5, String.valueOf(house.getTypeOfConstruction()));
            statement.setInt(6, house.getStreetNameId().getId());

            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return house;
    }

    /**
     * @param id
     * @throws SQLException
     */
    public void deleteHouseById(int id) throws SQLException {
        String sql = "DELETE FROM House WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, id);
        int rowsDeleted = statement.executeUpdate();
        if (rowsDeleted == 0) {
            throw new SQLException("Failed to delete record with id=" + id);
        }
        statement.close();
    }

    /**
     * @param id
     * @throws SQLException
     */
    public void deleteStreetById(int id) throws SQLException {
        String sql = "DELETE FROM Street WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, id);
        int rowsDeleted = statement.executeUpdate();
        if (rowsDeleted == 0) {
            throw new SQLException("Failed to delete record with id=" + id);
        }
        statement.close();
    }

    /**
     * @param street
     * @throws SQLException
     */
    public void deleteStreetByEntity(Street street) throws SQLException {
        deleteStreetById(street.getId());
    }

    /**
     * @param house
     * @throws SQLException
     */
    public void deleteHouseByEntity(House house) throws SQLException {
        deleteHouseById(house.getId());
    }

    /**
     * @throws SQLException
     */
    public void deleteAllHouses() throws SQLException {
        String sql = "truncate table House";
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
        statement.close();
    }

    /**
     * @throws SQLException
     */
    public void deleteAllStreets() throws SQLException {
        String sql = "set foreign_key_checks = 0; truncate table Street";
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
        statement.close();
    }

    /**
     * @param house
     * @return House
     */
    public House updateHouse(House house){
        String sql = "UPDATE House SET id = ?, houseName = ?, dateOfConstruction = ?, quantityOfFloors = ?, typeOfConstruction = ?, streetNameId = ? WHERE id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(7, house.getId());

            statement.setInt(1, house.getId());
            statement.setString(2, house.getHouseName());
            statement.setDate(3, house.getDateOfConstruction());
            statement.setInt(4, house.getQuantityOfFloors());
            statement.setString(5, String.valueOf(house.getTypeOfConstruction()));
            statement.setInt(6, house.getStreetNameId().getId());

            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return house;
    }

    /**
     * @param street
     * @return Street
     */
    public Street updateStreet(Street street){
        String sql = "UPDATE Street SET id=?, streetName=?, postcode=? WHERE id=?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(4, street.getId());

            statement.setInt(1, street.getId());
            statement.setString(2, street.getStreetName());
            statement.setInt(3, street.getPostcode());

            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return street;
    }

    /**
     * @param id
     * @return House
     * @throws SQLException
     */
    public House getHouseById(int id) throws SQLException {
        String sql = "SELECT * FROM House JOIN Street ON House.streetNameId = Street.id WHERE House.id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, id);

        ResultSet rs = statement.executeQuery();
        rs.next();
        id = rs.getInt("House.id");
        String houseName = rs.getString("House.houseName");
        Date dateOfConstruction = rs.getDate("House.dateOfConstruction");
        int quantityOfFloors = rs.getInt("House.quantityOfFloors");
        ConstructionTypes typeOfConstruction = ConstructionTypes.valueOf(rs.getString("House.typeOfConstruction"));
        id = rs.getInt("Street.id");
        String streetName = rs.getString("Street.streetName");
        int postcode = rs.getInt("Street.postcode");

        Street street = new Street(id, streetName, postcode);

        House house = new House(id, houseName, dateOfConstruction, quantityOfFloors, typeOfConstruction, street);

        statement.close();
        return house;
    }

    /**
     * @param id
     * @return Street
     * @throws SQLException
     */
    public Street getStreetById(int id) throws SQLException {
        String sql = "SELECT * FROM Street WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, id);

        ResultSet rs = statement.executeQuery();
        rs.next();

        id = rs.getInt("id");
        String streetName = rs.getString("streetName");
        int postcode = rs.getInt("postcode");
        statement.close();

        Street street = new Street(id, streetName, postcode);

        List<House> houses = getAllByVId(id);
        street.setHouses(houses);

        return street;
    }

    /**
     * @return List House
     * @throws SQLException
     */
    public List<House> getAllHouses() throws SQLException {
        String sql = "SELECT * FROM House JOIN Street ON House.streetNameId = Street.id";
        PreparedStatement statement = connection.prepareStatement(sql);

        ResultSet rs = statement.executeQuery();
        List<House> houses = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("House.id");
            String houseName = rs.getString("House.houseName");
            Date dateOfConstruction = rs.getDate("House.dateOfConstruction");
            int quantityOfFloors = rs.getInt("House.quantityOfFloors");
            ConstructionTypes typeOfConstruction = ConstructionTypes.valueOf(rs.getString("House.typeOfConstruction"));
            int streetId = rs.getInt("Street.id");
            String streetName = rs.getString("Street.streetName");
            int postcode = rs.getInt("Street.postcode");

            Street street = new Street(streetId, streetName, postcode);

            House house = new House(id, houseName, dateOfConstruction, quantityOfFloors, typeOfConstruction, street);
            houses.add(house);
        }

        statement.close();
        return houses;
    }

    /**
     * @return List Street
     * @throws SQLException
     */
    public List<Street> getAllStreets() throws SQLException {
        String sql = "SELECT * FROM Street";
        PreparedStatement statement = connection.prepareStatement(sql);

        ResultSet rs = statement.executeQuery();
        List<Street> streets = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String streetName = rs.getString("streetName");
            int postcode = rs.getInt("postcode");

            Street street = new Street(id, streetName, postcode);
            streets.add(street);
        }

        statement.close();

        streets.forEach(p -> {
            try {
                p.setHouses(getAllByVId(p.getId()));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        return streets;
    }

    /**
     * @param parentId
     * @return List House
     * @throws SQLException
     */
    public List<House> getAllByVId(int parentId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM House JOIN Street ON House.streetNameId = Street.id WHERE streetNameId = ?");

        statement.setLong(1, parentId);
        ResultSet rs = statement.executeQuery();
        List<House> houses = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("House.id");
            String houseName = rs.getString("House.houseName");
            Date dateOfConstruction = rs.getDate("House.dateOfConstruction");
            int quantityOfFloors = rs.getInt("House.quantityOfFloors");
            ConstructionTypes typeOfConstruction = ConstructionTypes.valueOf(rs.getString("House.typeOfConstruction"));
            int streetId = rs.getInt("Street.id");
            String streetName = rs.getString("Street.streetName");
            int postcode = rs.getInt("Street.postcode");

            Street street = new Street(streetId, streetName, postcode);

            House house = new House(id, houseName, dateOfConstruction, quantityOfFloors, typeOfConstruction, street);
            houses.add(house);
        }

        statement.close();
        return houses;
    }
}

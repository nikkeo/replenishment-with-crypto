package org.example.Services;

import lombok.Getter;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.example.Entities.House;
import org.example.Entities.Street;
import org.example.Mappers.HouseMapper;
import org.example.Mappers.StreetMapper;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

@Getter
public class MyBatisInterfaseRealization implements DatabaseInterface{
    private String resource;
    private InputStream inputStream;
    private SqlSessionFactory sqlSessionFactory;
    SqlSession session;

    StreetMapper streetMapper;

    HouseMapper houseMapper;

    /**
     * @throws IOException
     */
    public MyBatisInterfaseRealization() throws IOException {
        resource = "mybatis-config.xml";
        inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        session = sqlSessionFactory.openSession();
        streetMapper = session.getMapper(StreetMapper.class);
        houseMapper = session.getMapper(HouseMapper.class);
    }

    /**
     * @param street
     * @return Street
     */
    @Override
    public Street saveStreet(Street street) {
        streetMapper.saveStreet(street);
        session.commit();
        return street;
    }

    /**
     * @param house
     * @return House
     */
    @Override
    public House saveHouse(House house) {
        houseMapper.saveHouse(house);
        session.commit();
        return house;
    }

    /**
     * @param id
     * @throws SQLException
     */
    @Override
    public void deleteHouseById(int id) throws SQLException {
        houseMapper.deleteHouseById(id);
        session.commit();
    }

    /**
     * @param id
     * @throws SQLException
     */
    @Override
    public void deleteStreetById(int id) throws SQLException {
        streetMapper.deleteStreetById(id);
        session.commit();
    }

    /**
     * @param street
     * @throws SQLException
     */
    @Override
    public void deleteStreetByEntity(Street street) throws SQLException {
        streetMapper.deleteConnectedHouses(street.getId());
        streetMapper.deleteStreetById(street.getId());
        session.commit();
    }

    /**
     * @param house
     * @throws SQLException
     */
    @Override
    public void deleteHouseByEntity(House house) throws SQLException {
        houseMapper.deleteHouseById(house.getId());
        session.commit();
    }

    /**
     * @throws SQLException
     */
    @Override
    public void deleteAllHouses() throws SQLException {
        List<House> houses = houseMapper.getAllHouses();
        houses.forEach(x -> houseMapper.deleteHouseById(x.getId()));
        session.commit();
    }

    /**
     * @throws SQLException
     */
    @Override
    public void deleteAllStreets() throws SQLException {
        List<Street> streets = streetMapper.getAllStreets();
        streets.forEach(x -> streetMapper.deleteStreetById(x.getId()));
        session.commit();
    }

    /**
     * @param house
     * @return House
     */
    @Override
    public House updateHouse(House house) {
        houseMapper.updateHouse(house);
        session.commit();
        return house;
    }

    /**
     * @param street
     * @return Street
     */
    @Override
    public Street updateStreet(Street street) {
        streetMapper.updateStreet(street);
        session.commit();
        return street;
    }

    /**
     * @param id
     * @return House
     * @throws SQLException
     */
    @Override
    public House getHouseById(int id) throws SQLException {
        return houseMapper.getHouseById(id);
    }

    /**
     * @param id
     * @return Street
     * @throws SQLException
     */
    @Override
    public Street getStreetById(int id) throws SQLException {
        return streetMapper.getStreetById(id);
    }

    /**
     * @return List House
     * @throws SQLException
     */
    @Override
    public List<House> getAllHouses() throws SQLException {
        return houseMapper.getAllHouses();
    }

    /**
     * @return List Street
     * @throws SQLException
     */
    @Override
    public List<Street> getAllStreets() throws SQLException {
        return streetMapper.getAllStreets();
    }

    /**
     * @param parentId
     * @return List House
     * @throws SQLException
     */
    @Override
    public List<House> getAllByVId(int parentId) throws SQLException {
        return houseMapper.getHousesByVId(parentId);
    }
}

package org.example.Mappers;

import lombok.Value;
import org.apache.ibatis.annotations.*;
import org.example.Entities.House;

import javax.persistence.JoinColumn;
import java.util.List;

public interface HouseMapper {
    final String getAll = "SELECT * FROM House";
    final String getById = "SELECT * FROM House WHERE ID = #{id}";

    final String getByVId = "SELECT * FROM House WHERE streetNameId = #{id}";
    final String deleteById = "DELETE from House WHERE ID = #{id}";
    final String insert = "INSERT INTO House (id, houseName, dateOfConstruction, quantityOfFloors, typeOfConstruction, streetNameId ) VALUES (#{id}, #{houseName}, #{dateOfConstruction}, #{quantityOfFloors}, #{typeOfConstruction}, ${streetNameId.getId()})";
    final String update = "UPDATE House SET id = #{id}, houseName = #{houseName}, dateOfConstruction = #{dateOfConstruction}, quantityOfFloors = #{quantityOfFloors}, typeOfConstruction = #{typeOfConstruction}, streetNameId = ${streetNameId.getId()} WHERE ID = #{id}";

    @Insert(insert)
    void saveHouse(House house);

    @Delete(deleteById)
    void deleteHouseById(int id);

    @Update(update)
    void updateHouse(House house);

    @Select(getById)
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "houseName", column = "houseName"),
            @Result(property = "dateOfConstruction", column = "dateOfConstruction"),
            @Result(property = "quantityOfFloors", column = "quantityOfFloors"),
            @Result(property = "typeOfConstruction", column = "typeOfConstruction"),
            @Result(property = "streetNameId", column = "streetNameId",
                    one = @One(select = "org.example.Mappers.StreetMapper.getStreetById"))
    })
    House getHouseById(int id);

    @Select(getByVId)
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "houseName", column = "houseName"),
            @Result(property = "dateOfConstruction", column = "dateOfConstruction"),
            @Result(property = "quantityOfFloors", column = "quantityOfFloors"),
            @Result(property = "typeOfConstruction", column = "typeOfConstruction"),
            @Result(property = "streetNameId", column = "streetNameId",
                    one = @One(select = "org.example.Mappers.StreetMapper.getStreetById"))
    })
    List<House> getHousesByVId(int id);

    @Select(getAll)
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "houseName", column = "houseName"),
            @Result(property = "dateOfConstruction", column = "dateOfConstruction"),
            @Result(property = "quantityOfFloors", column = "quantityOfFloors"),
            @Result(property = "typeOfConstruction", column = "typeOfConstruction"),
            @Result(property = "streetNameId", column = "streetNameId",
                    one = @One(select = "org.example.Mappers.StreetMapper.getStreetById"))
    })
    List<House> getAllHouses();
}

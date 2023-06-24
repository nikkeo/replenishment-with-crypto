package org.example.Mappers;

import lombok.Value;
import org.apache.ibatis.annotations.*;
import org.example.Entities.Street;

import java.util.List;

public interface StreetMapper {
    final String getAll = "SELECT * FROM Street";
    final String getById = "SELECT * FROM Street WHERE ID = #{id}";
    final String deletePrepatations = "DELETE from House where streetNameId = #{id}";
    final String deleteById = "DELETE from Street WHERE ID = #{id}";
    final String insert = "INSERT INTO Street (id, streetName, postcode) VALUES (#{id}, #{streetName}, #{postcode})";
    final String update = "UPDATE Street SET id = #{id}, streetName = #{streetName}, postcode = #{postcode} WHERE ID = #{id}";

    @Insert(insert)
    void saveStreet(Street street);

    @Delete(deletePrepatations)
    void deleteConnectedHouses(int id);

    @Delete(deleteById)
    void deleteStreetById(int id);

    @Update(update)
    void updateStreet(Street street);

    @Select(getById)
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "streetName", column = "streetName"),
            @Result(property = "postcode", column = "postcode"),
            @Result(property = "houses", column = "id",
                    one = @One(select = "org.example.Mappers.HouseMapper.getHousesByVId"))
    })
    Street getStreetById(int id);

    @Select(getAll)
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "streetName", column = "streetName"),
            @Result(property = "postcode", column = "postcode"),
            @Result(property = "houses", column = "id",
                    one = @One(select = "org.example.Mappers.HouseMapper.getHousesByVId"))
    })
    List<Street> getAllStreets();
}

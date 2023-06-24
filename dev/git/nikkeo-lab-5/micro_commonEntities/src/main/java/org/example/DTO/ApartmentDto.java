package org.example.DTO;

import lombok.Getter;
import lombok.Setter;
import org.example.Entities.HouseV2;
import org.example.Entities.UserV2;

import java.io.Serializable;

@Setter
@Getter
public class ApartmentDto implements Serializable {
    private int id;
    private int number;
    private int area;
    private int numberOfRooms;
    private HouseV2 houseId;
    private UserV2 user;

    public ApartmentDto(int id, int number, int area, int numberOfRooms){
        this.id = id;
        this.number = number;
        this.area = area;
        this.numberOfRooms = numberOfRooms;
    }

    public ApartmentDto(int number, int area, int numberOfRooms){
        this.number = number;
        this.area = area;
        this.numberOfRooms = numberOfRooms;
    }
    public ApartmentDto() { }
}

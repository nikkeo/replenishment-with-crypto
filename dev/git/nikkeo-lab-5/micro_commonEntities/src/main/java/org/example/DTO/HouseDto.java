package org.example.DTO;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.example.Entities.ConstructionTypes;
import org.example.Entities.ApartmentV2;
import org.example.Entities.StreetV2;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Getter
@NonNull
@Setter
public class HouseDto implements Serializable {
    private int id;
    private String houseName;
    private Date dateOfConstruction;
    private int quantityOfFloors;
    private ConstructionTypes typeOfConstruction;
    private StreetV2 streetNameId;
    private String material;
    List<ApartmentV2> apartments = new ArrayList<>();

    /**
     * Main constructor
     * @param id
     * @param houseName
     * @param dateOfConstruction
     * @param quantityOfFloors
     * @param typeOfConstruction
     * @param streetNameID
     */
    public HouseDto(int id, String houseName, Date dateOfConstruction, int quantityOfFloors, ConstructionTypes typeOfConstruction, StreetV2 streetNameID, String material){
        this.id = id;
        this.houseName = houseName;
        this.dateOfConstruction = dateOfConstruction;
        this.quantityOfFloors = quantityOfFloors;
        this.typeOfConstruction = typeOfConstruction;
        this.streetNameId = streetNameID;
        this.material = material;
    }
    public HouseDto(int id, String houseName, Date dateOfConstruction, int quantityOfFloors, ConstructionTypes typeOfConstruction, String material){
        this.id = id;
        this.houseName = houseName;
        this.dateOfConstruction = dateOfConstruction;
        this.quantityOfFloors = quantityOfFloors;
        this.typeOfConstruction = typeOfConstruction;
        this.material = material;
    }

    /**
     * Empty constructor
     */
    public HouseDto() {}
}


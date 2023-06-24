package org.example.DTO;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.example.Entities.HouseV2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@NonNull
@Getter
@Setter
public class StreetDto implements Serializable {
    private int id;
    private String streetName;
    private int postcode;
    List<HouseV2> Houses = new ArrayList<>();

    /**
     * Main constructor
     *
     * @param id
     * @param streetName
     * @param postcode
     */
    public StreetDto(int id, String streetName, int postcode){
        this.id = id;
        this.streetName = streetName;
        this.postcode = postcode;
    }

    public StreetDto(String streetName, int postcode){
        this.streetName = streetName;
        this.postcode = postcode;
    }

    /**
     * Empty constructor
     */
    public StreetDto() {}
}
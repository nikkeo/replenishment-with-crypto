package org.example.Entities;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "House")
@Getter @NonNull
public class House {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "houseName")
    private String houseName;
    @Column(name = "dateOfConstruction")
    private Date dateOfConstruction;
    @Column(name = "quantityOfFloors")
    private int quantityOfFloors;
    @Enumerated(EnumType.STRING)
    @Column(name = "typeOfConstruction")
    private ConstructionTypes typeOfConstruction;
    @ManyToOne(fetch = FetchType.LAZY) @Setter
    private Street streetNameId;

    /**
     * Main constructor
     * @param id
     * @param houseName
     * @param dateOfConstruction
     * @param quantityOfFloors
     * @param typeOfConstruction
     * @param streetNameID
     */
    public House(int id, String houseName, Date dateOfConstruction, int quantityOfFloors, ConstructionTypes typeOfConstruction, Street streetNameID){
        this.id = id;
        this.houseName = houseName;
        this.dateOfConstruction = dateOfConstruction;
        this.quantityOfFloors = quantityOfFloors;
        this.typeOfConstruction = typeOfConstruction;
        this.streetNameId = streetNameID;
    }

    /**
     * Empty constructor
     */
    public House() {}
}

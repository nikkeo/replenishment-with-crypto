package org.example.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "House")
@Getter @NonNull
public class HouseV2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "houseName")
    private String houseName;
    @Column(name = "dateOfConstruction")
    private Date dateOfConstruction;
    @Column(name = "quantityOfFloors")
    private Integer quantityOfFloors;
    @Enumerated(EnumType.STRING)
    @Column(name = "typeOfConstruction")
    private ConstructionTypes typeOfConstruction;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "street_id") @Setter
    @JsonBackReference
    private StreetV2 streetNameId;
    @Column(name = "material")
    private String material;
    @OneToMany(mappedBy = "houseId", cascade = CascadeType.ALL) @Setter
    @JsonManagedReference
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
    public HouseV2(int id, String houseName, Date dateOfConstruction, int quantityOfFloors, ConstructionTypes typeOfConstruction, StreetV2 streetNameID, String material){
        this.id = id;
        this.houseName = houseName;
        this.dateOfConstruction = dateOfConstruction;
        this.quantityOfFloors = quantityOfFloors;
        this.typeOfConstruction = typeOfConstruction;
        this.streetNameId = streetNameID;
        this.material = material;
    }

    /**
     * Empty constructor
     */
    public HouseV2() {}
}

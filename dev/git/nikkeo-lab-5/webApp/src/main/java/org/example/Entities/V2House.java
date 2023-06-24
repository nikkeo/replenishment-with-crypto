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
public class V2House {
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
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "street_id") @Setter
    @JsonBackReference
    private V2Street streetNameId;
    @Column(name = "material")
    private String material;
    @OneToMany(mappedBy = "houseId", cascade = CascadeType.ALL) @Setter
    @JsonManagedReference
    List<V2Apartment> apartments = new ArrayList<>();

    /**
     * Main constructor
     * @param id
     * @param houseName
     * @param dateOfConstruction
     * @param quantityOfFloors
     * @param typeOfConstruction
     * @param streetNameID
     */
    public V2House(int id, String houseName, Date dateOfConstruction, int quantityOfFloors, ConstructionTypes typeOfConstruction, V2Street streetNameID, String material){
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
    public V2House() {}
}

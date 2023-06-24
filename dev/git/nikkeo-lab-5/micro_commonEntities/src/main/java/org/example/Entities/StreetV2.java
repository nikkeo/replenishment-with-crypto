package org.example.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Street")
@Getter @NonNull
public class StreetV2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "streetName")
    private String streetName;
    @Column(name = "postcode")
    private Integer postcode;
    @OneToMany(mappedBy = "streetNameId", cascade = CascadeType.ALL) @Setter
    @JsonManagedReference
    List<HouseV2> Houses = new ArrayList<>();

    /**
     * Main constructor
     *
     * @param id
     * @param streetName
     * @param postcode
     */
    public StreetV2(int id, String streetName, int postcode){
        this.streetName = streetName;
        this.postcode = postcode;
    }

    public StreetV2(String streetName, int postcode){
        this.streetName = streetName;
        this.postcode = postcode;
    }

    /**
     * Empty constructor
     */
    public StreetV2() {}
}

package org.example.Entities;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Street")
@Getter @NonNull
public class Street {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "streetName")
    private String streetName;
    @Column(name = "postcode")
    private int postcode;

    @OneToMany(mappedBy = "streetNameId", cascade = CascadeType.ALL) @Setter
    List<House> houses = new ArrayList<>();

    /**
     * Main constructor
     * @param id
     * @param streetName
     * @param postcode
     */
    public Street(int id, String streetName, int postcode){
        this.id = id;
        this.streetName = streetName;
        this.postcode = postcode;
    }

    /**
     * Empty constructor
     */
    public Street() {}
}

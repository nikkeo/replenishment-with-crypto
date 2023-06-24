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
public class V2Street {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "streetName")
    private String streetName;
    @Column(name = "postcode")
    private int postcode;
    @OneToMany(mappedBy = "streetNameId", cascade = CascadeType.ALL) @Setter
    @JsonManagedReference
    List<V2House> Houses = new ArrayList<>();

    /**
     * Main constructor
     * @param id
     * @param streetName
     * @param postcode
     */
    public V2Street(int id, String streetName, int postcode){
        this.id = id;
        this.streetName = streetName;
        this.postcode = postcode;
    }

    /**
     * Empty constructor
     */
    public V2Street() {}
}

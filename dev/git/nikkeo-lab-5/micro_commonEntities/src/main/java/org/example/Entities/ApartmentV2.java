package org.example.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Apartment")
@Getter
public class ApartmentV2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "number")
    private Integer number;

    @Column(name = "area")
    private Integer area;

    @Column(name = "number_of_rooms")
    private Integer numberOfRooms;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "house_id")
    @JsonBackReference
    private HouseV2 houseId;

    @OneToOne
    @JsonManagedReference
    @JoinColumn(name = "user_id") @Setter
    private UserV2 user;

    public ApartmentV2(int id, int number, int area, int numberOfRooms, HouseV2 houseId){
        this.id = id;
        this.number = number;
        this.area = area;
        this.numberOfRooms = numberOfRooms;
        this.houseId = houseId;
    }

    public ApartmentV2(int number, int area, int numberOfRooms, HouseV2 houseId){
        this.number = number;
        this.area = area;
        this.numberOfRooms = numberOfRooms;
        this.houseId = houseId;
    }

    public ApartmentV2() { }
}
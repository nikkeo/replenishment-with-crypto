package org.example.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Apartment")
@Getter
public class V2Apartment {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "number")
    private int number;

    @Column(name = "area")
    private int area;

    @Column(name = "number_of_rooms")
    private int numberOfRooms;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "house_id")
    @JsonBackReference
    private V2House houseId;

    @OneToOne
    @JsonManagedReference
    @JoinColumn(name = "user_id") @Setter
    private V2User user;

    public V2Apartment(int id, int number, int area, int numberOfRooms, V2House houseId){
        this.id = id;
        this.number = number;
        this.area = area;
        this.numberOfRooms = numberOfRooms;
        this.houseId = houseId;
    }

    public V2Apartment () { }
}

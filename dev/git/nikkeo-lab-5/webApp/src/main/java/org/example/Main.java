package org.example;


import org.example.Repository.ApartmentRepository;
import org.example.Repository.HouseRepository;
import org.example.Repository.StreetRepository;
import org.example.Repository.UserRepository;
import org.example.Services.JPAService;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.example.Entities.*;

import java.sql.Date;
import java.util.List;

@SpringBootApplication
public class Main {
    @Autowired
    PasswordEncoder encoder;
    JPAService jpa;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Autowired
    public void JPAServiceBean(StreetRepository streetRepository, HouseRepository houseRepository, ApartmentRepository apartmentRepository, UserRepository userRepository) {
        this.jpa = new JPAService(streetRepository, houseRepository, apartmentRepository, userRepository);
    }

    @Bean
    CommandLineRunner run(){
        return args -> {
            V2User admin = new V2User("admin", encoder.encode("admin"));
            admin.setV2Role(new V2Role(UserTypes.ROLE_ADMIN));

            jpa.saveUser(admin);
        };
    }
    @Autowired
    public void tests() throws Exception {
        Date dateOfConstruction = new Date(System.currentTimeMillis());
        ConstructionTypes constructionType = ConstructionTypes.Garage;
        V2Street v2Street = new V2Street(2233333, "Street", 12345);
        V2House v2House = new V2House(2233333, "house", dateOfConstruction, 6, constructionType, v2Street, "red brick");
        V2House v2Housee = new V2House(2233334, "house", dateOfConstruction, 6, constructionType, v2Street, "red brick");
        V2House v2Houseee = new V2House(2233335, "house", dateOfConstruction, 6, constructionType, v2Street, "red brick");
        V2User v2User = new V2User("123", encoder.encode("123"));
        V2Apartment v2Apartment = new V2Apartment(2233333, 123, 123, 123, v2House);
        v2Apartment.setUser(v2User);

        jpa.saveStreet(v2Street);
        jpa.saveHouse(v2House);
        jpa.saveHouse(v2Housee);
        jpa.saveHouse(v2Houseee);
        jpa.saveUser(v2User);
        jpa.saveApartment(v2Apartment);
        List<V2Street> v2Streets = jpa.getAllStreets();
        List<V2House> v2Houses = jpa.getAllHouses();
        List<V2Apartment> v2Apartments = jpa.getAllApartments();
        List<V2User> users = jpa.getAllUsers();

        List<V2House> houses = jpa.getAllHousesByVId(v2Street.getId());
        List<V2Apartment> apartments = jpa.getAllApartmentsByVId(v2House.getId());
        List<V2House> houseByApartments = jpa.getAllHousesByNumber(v2Apartment.getNumber());
        List<V2Street> streetsByHouseName = jpa.getAllStreetsByName(v2House.getHouseName());
    }
}
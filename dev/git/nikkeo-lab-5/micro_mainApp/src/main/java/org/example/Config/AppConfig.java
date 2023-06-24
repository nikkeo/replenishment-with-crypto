package org.example.Config;

import org.example.Converter.Converter;
import org.example.Converter.ConverterRealization;
import org.example.Entities.*;
import org.example.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Date;


@Configuration
public class AppConfig {

    private PasswordEncoder encoder;
    private UserService userService;

    @Autowired
    public AppConfig(PasswordEncoder encoder, UserService userService){
        this.encoder = encoder;
        this.userService = userService;
    }


    @Bean
    CommandLineRunner run(){
        return args -> {
            UserV2 admin = new UserV2("admin", encoder.encode("admin"));
            admin.setRoleV2(new RoleV2(UserTypes.ROLE_ADMIN));

            userService.saveUser(admin);
        };
    }

    @Bean
    Converter converter() {
        return new ConverterRealization();
    }
    @Autowired
    public void tests() throws Exception {
        Date dateOfConstruction = new Date(System.currentTimeMillis());
        ConstructionTypes constructionType = ConstructionTypes.Garage;
        StreetV2 v2Street = new StreetV2("Street", 12345);
        HouseV2 v2House = new HouseV2(2233333, "house", dateOfConstruction, 6, constructionType, v2Street, "red brick");
        HouseV2 v2Housee = new HouseV2(2233334, "house", dateOfConstruction, 6, constructionType, v2Street, "red brick");
        HouseV2 v2Houseee = new HouseV2(2233335, "house", dateOfConstruction, 6, constructionType, v2Street, "red brick");
        UserV2 v2User = new UserV2("123", encoder.encode("123"));
        ApartmentV2 v2Apartment = new ApartmentV2(2233333, 123, 123, 123, v2House);
        v2Apartment.setUser(v2User);
    }
}


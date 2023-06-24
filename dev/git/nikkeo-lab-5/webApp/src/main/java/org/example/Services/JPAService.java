package org.example.Services;

import org.example.Controllers.ApartmentController;
import org.example.Controllers.HouseController;
import org.example.Controllers.StreetController;
import org.example.Controllers.UserController;
import org.example.Entities.V2Apartment;
import org.example.Entities.V2House;
import org.example.Entities.V2Street;
import org.example.Entities.V2User;
import org.example.Repository.ApartmentRepository;
import org.example.Repository.HouseRepository;
import org.example.Repository.StreetRepository;
import org.example.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JPAService {
    private StreetController streetService;
    private HouseController houseController;
    private ApartmentController apartmentController;
    private UserController userController;

    public JPAService(StreetRepository streetRepository, HouseRepository houseRepository, ApartmentRepository apartmentRepository, UserRepository userRepository) {

        streetService = new StreetController(new StreetService(streetRepository));
        houseController = new HouseController(new HouseService(houseRepository));
        apartmentController = new ApartmentController(new ApartmentService(apartmentRepository));
        userController = new UserController(new UserService(userRepository));
    }

    public List<V2Street> getAllStreets() {
        return (List<V2Street>) streetService.getAllStreets();
    }
    public V2Street saveStreet(V2Street street) {
        return streetService.saveStreet(street);
    }

    public List<V2House> getAllHousesByVId(int streetNameId) { return houseController.getAllHousesByVId(streetNameId); }

    public List<V2House> getAllHouses() {return (List<V2House>) houseController.getAllHouses();}
    public V2House saveHouse(V2House house) {return houseController.saveHouse(house);}

    public List<V2Apartment> getAllApartmentsByVId(int houseId) { return apartmentController.getAllApartmentsByVId(houseId); }

    public List<V2Apartment> getAllApartments() { return (List<V2Apartment>) apartmentController.getAllApartments(); }

    public V2Apartment saveApartment(V2Apartment v2Apartment) { return apartmentController.saveApartment(v2Apartment); }

    public List<V2House> getAllHousesByNumber(int apartmentNumber){
        List<V2Apartment> apartments = apartmentController.getByNumber(apartmentNumber);
        List<V2House> houses = new ArrayList<>();
        apartments.forEach(x -> houses.add(x.getHouseId()));

        return houses;
    }

    public List<V2Street> getAllStreetsByName(String houseName){
        List<V2House> houses = houseController.getByName(houseName);
        List<V2Street> streets = new ArrayList<>();
        houses.forEach(x -> streets.add(x.getStreetNameId()));

        return streets;
    }

    public List<V2User> getAllUsers() {
        return (List<V2User>) userController.getAllUsers();
    }

    public V2User saveUser(V2User user) throws Exception {
        return userController.saveUser(user);
    }

    public V2User getUserById(int id) { return userController.getUserById(id); }

    public void deleteUser(int id) { userController.deleteUserById(id); }

    public V2User updateUser(V2User user) { return userController.updateUser(user); }
}

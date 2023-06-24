package org.example.Services;

import org.example.Entities.V2Apartment;
import org.example.Repository.ApartmentRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class ApartmentService {
    private ApartmentRepository apartmentRepository;

    public ApartmentService(ApartmentRepository apartmentRepository){
        this.apartmentRepository = apartmentRepository;
    }

    public Iterable<V2Apartment> getAllApartments() {
        return apartmentRepository.findAll();
    }

    public V2Apartment getApartmentById(int id) {
        if (!isFlatOwner(id))
            throw new SecurityException("Access denied");
        return apartmentRepository.findById(Long.valueOf(id)).orElse(null);
    }

    public boolean isFlatOwner(int flatId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        List<V2Apartment> apartments = (List<V2Apartment>) getAllApartments();
        if (!apartments.stream().filter(x -> x.getId() == flatId).findFirst().isEmpty()){
            if (apartments.stream().filter(x -> x.getId() == flatId).findFirst().get().getUser().getUsername() == username){
                return true;
            }
        }
        return false;
    }

    public V2Apartment saveApartment(V2Apartment apartment) {
        return apartmentRepository.save(apartment);
    }

    public void deleteApartmentById(int id) {
        apartmentRepository.deleteById(Long.valueOf(id));
    }

    public V2Apartment updateApartment(V2Apartment updatedApartment) {
        return apartmentRepository.save(updatedApartment);
    }

    public List<V2Apartment> getAllApartmentsByVId(int houseId) {
        return apartmentRepository.findAll().stream().filter(x -> x.getHouseId().getId() == houseId).toList();
    }

    public List<V2Apartment> getByNumber(int number){
        return apartmentRepository.findAll().stream().filter(x -> x.getNumber() == number).toList();
    }
}

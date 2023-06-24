package org.example.Controllers;

import org.example.Entities.V2Apartment;
import org.example.Services.ApartmentService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apartment")
public class ApartmentController {
    private ApartmentService apartmentService;

    public ApartmentController(ApartmentService apartmentService){
        this.apartmentService = apartmentService;
    }
    @GetMapping(value = "/getAll", produces = "application/json")
    public Iterable<V2Apartment> getAllApartments() {
        return apartmentService.getAllApartments();
    }
    @GetMapping(value = "/getById/{id}", produces = "application/json")
    public V2Apartment getApartmentById(@PathVariable("id") int id) {
        return apartmentService.getApartmentById(id);
    }

    @PostMapping(value = "/save", produces = "application/json")
    public V2Apartment saveApartment(@RequestBody V2Apartment apartment) {
        return apartmentService.saveApartment(apartment);
    }
    @DeleteMapping(value = "/deleteBy/{id}", produces = "application/json")
    public void deleteApartmentById(@PathVariable("id") int id) {
        apartmentService.deleteApartmentById(id);
    }
    @PutMapping(value = "/update", produces = "application/json")
    public V2Apartment updateApartment(@RequestBody V2Apartment updatedApartment) {
        return apartmentService.saveApartment(updatedApartment);
    }
    @GetMapping(value = "/getByVid/{houseId}", produces = "application/json")
    public List<V2Apartment> getAllApartmentsByVId(@PathVariable("houseId") int houseId) {
        return apartmentService.getAllApartmentsByVId(houseId);
    }
    @GetMapping(value = "/getByName/{number}", produces = "application/json")
    public List<V2Apartment> getByNumber(@PathVariable("number") int number){
        return apartmentService.getByNumber(number);
    }
}
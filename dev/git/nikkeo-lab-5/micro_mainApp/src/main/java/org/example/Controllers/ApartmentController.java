package org.example.Controllers;

import org.example.Consumer.ApartmentConsumer;
import org.example.DTO.ApartmentDto;
import org.example.Entities.ApartmentV2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apartment")
public class ApartmentController {
    private final ApartmentConsumer apartmentConsumer;

    public ApartmentController(ApartmentConsumer apartmentConsumer){
        this.apartmentConsumer = apartmentConsumer;
    }

    @GetMapping(value = "/getAll", produces = "application/json")
    public List<ApartmentDto> getAllApartments() {
        return apartmentConsumer.getAllApartments();
    }

    @GetMapping(value = "/getById/{id}", produces = "application/json")
    public ApartmentDto getApartmentById(@PathVariable("id") int id) throws Exception {
        return apartmentConsumer.getApartmentById(id);
    }

    @PostMapping(value = "/save", produces = "application/json")
    public ApartmentDto saveApartment(@RequestBody ApartmentDto apartment) throws Exception {
        return apartmentConsumer.saveApartment(apartment);
    }

    @DeleteMapping(value = "/deleteBy/{id}", produces = "application/json")
    public void deleteApartmentById(@PathVariable("id") int id) {
        apartmentConsumer.deleteApartmentById(id);
    }

    @PutMapping(value = "/update", produces = "application/json")
    public ApartmentDto updateApartment(@RequestBody ApartmentDto updatedApartment) throws Exception {
        return apartmentConsumer.updateApartment(updatedApartment);
    }
    @GetMapping(value = "/getByVid/{houseId}", produces = "application/json")
    public List<ApartmentDto> getAllApartmentsByVId(@PathVariable("houseId") int houseId) {
        return apartmentConsumer.getAllApartmentsByVId(houseId);
    }
    @GetMapping(value = "/getByName/{number}", produces = "application/json")
    public List<ApartmentDto> getByNumber(@PathVariable("number") int number){
        return apartmentConsumer.getByNumber(number);
    }
}
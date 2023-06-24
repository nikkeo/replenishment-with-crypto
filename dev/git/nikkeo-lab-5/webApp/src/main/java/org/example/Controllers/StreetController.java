package org.example.Controllers;

import org.example.Entities.V2Street;
import org.example.Services.StreetService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/street")
public class StreetController {
    private StreetService streetService;

    public StreetController(StreetService streetService){
        this.streetService = streetService;
    }
    @GetMapping(value = "getAll", produces = "application/json")
    public Iterable<V2Street> getAllStreets() {
        return streetService.getAllStreets();
    }
    @GetMapping(value = "/getById/{id}", produces = "application/json")
    public V2Street getStreetById(@PathVariable("id") int id) {
        return streetService.getStreetById(id);
    }
    @PostMapping(value = "/save", produces = "application/json")
    public V2Street saveStreet(@RequestBody V2Street street) {
        return streetService.saveStreet(street);
    }

    @DeleteMapping(value = "/deleteBy/{id}", produces = "application/json")
    public void deleteStreetById(@PathVariable("id") int id) {
        streetService.deleteStreetById(id);
    }

    public Optional<V2Street> getStreetById(Long id) {
        return streetService.getStreetById(id);
    }
    @PutMapping(value = "/update", produces = "application/json")
    public V2Street updateStreet(@RequestBody V2Street updatedStreet) {
        return streetService.updateStreet(updatedStreet);
    }
}


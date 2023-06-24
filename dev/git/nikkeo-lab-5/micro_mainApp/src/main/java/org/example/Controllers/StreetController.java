package org.example.Controllers;

import org.example.Consumer.StreetConsumer;
import org.example.DTO.StreetDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/street")
public class StreetController {
    private StreetConsumer streetConsumer;
    @Autowired
    public StreetController(StreetConsumer streetConsumer){
        this.streetConsumer = streetConsumer;
    }

    @GetMapping(value = "getAll", produces = "application/json")
    public List<StreetDto> getAllStreets() {
        return streetConsumer.getAllStreets();
    }

    @GetMapping(value = "/getById/{id}", produces = "application/json")
    public StreetDto getStreetById(@PathVariable("id") int id) {
        return streetConsumer.getStreetById(id);

    }

    @PostMapping(value = "/save", produces = "application/json")
    public StreetDto saveStreet(@RequestBody StreetDto street) {
        return streetConsumer.saveStreet(street);
    }

    @DeleteMapping(value = "/deleteBy/{id}", produces = "application/json")
    public void deleteStreetById(@PathVariable("id") int id) {
        streetConsumer.deleteStreetById(id);
    }

    @PutMapping(value = "/update", produces = "application/json")
    public StreetDto updateStreet(@RequestBody StreetDto updatedStreet) {
        return streetConsumer.updateStreet(updatedStreet);
    }
}


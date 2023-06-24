package org.example.Controllers;

import org.example.Consumer.HouseConsumer;
import org.example.Converter.Converter;
import org.example.DTO.HouseDto;
import org.example.Entities.HouseV2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/house")
public class HouseController {
    private HouseConsumer houseConsumer;
    @Autowired
    public HouseController(HouseConsumer houseConsumer){
        this.houseConsumer = houseConsumer;
    }

    @GetMapping(value = "getAll", produces = "application/json")
    public List<HouseDto> getAllHouses() {
        return houseConsumer.getAllHouses();
    }

    @GetMapping(value = "/getById/{id}", produces = "application/json")
    public HouseDto getHouseById(@PathVariable("id") int id) {
        return houseConsumer.getHouseById(id);
    }

    @PostMapping(value = "/save", produces = "application/json")
    public HouseDto saveHouse(@RequestBody HouseDto house) {
        return houseConsumer.saveHouse(house);
    }

    @DeleteMapping(value = "/deleteBy/{id}", produces = "application/json")
    public void deleteHouseById(@PathVariable("id") int id) {
        houseConsumer.deleteHouseById(id);
    }

    @PutMapping(value = "/update", produces = "application/json")
    public HouseDto updateHouse(@RequestBody HouseDto updatedHouse) {
        return houseConsumer.updateHouse(updatedHouse);
    }

    @GetMapping(value = "/getByVid/{streetNameId}", produces = "application/json")
    public List<HouseDto> getAllHousesByVId(@PathVariable("streetNameId") int streetNameId) {
        return houseConsumer.getAllHousesByVId(streetNameId);
    }

    @GetMapping(value = "/getByName/{name}", produces = "application/json")
    public List<HouseDto> getByName(@PathVariable("name") String name){
        return houseConsumer.getByName(name);
    }
}

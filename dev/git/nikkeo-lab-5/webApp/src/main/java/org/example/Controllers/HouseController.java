package org.example.Controllers;

import org.example.Entities.V2House;
import org.example.Services.HouseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/house")
public class HouseController {
    private HouseService houseService;

    public HouseController(HouseService houseService){
        this.houseService = houseService;
    }
    @GetMapping(value = "getAll", produces = "application/json")
    public Iterable<V2House> getAllHouses() {
        return houseService.getAllHouses();
    }
    @GetMapping(value = "/getById/{id}", produces = "application/json")
    public V2House getHouseById(@PathVariable("id") int id) {
        return houseService.getHouseById(id);
    }
    @PostMapping(value = "/save", produces = "application/json")
    public V2House saveHouse(@RequestBody V2House house) {
        return houseService.saveHouse(house);
    }
    @DeleteMapping(value = "/deleteBy/{id}", produces = "application/json")
    public void deleteHouseById(@PathVariable("id") int id) {
        houseService.deleteHouseById(id);
    }
    @PutMapping(value = "/update", produces = "application/json")
    public V2House updateHouse(@RequestBody V2House updatedHouse) {
        return houseService.updateHouse(updatedHouse);
    }
    @GetMapping(value = "/getByVid/{streetNameId}", produces = "application/json")
    public List<V2House> getAllHousesByVId(@PathVariable("streetNameId") int streetNameId) {
        return houseService.getAllHousesByVId(streetNameId);
    }
    @GetMapping(value = "/getByName/{name}", produces = "application/json")
    public List<V2House> getByName(@PathVariable("name") String name){
        return houseService.getByName(name);
    }
}

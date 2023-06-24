package org.example.Services;

import org.example.Entities.V2House;
import org.example.Repository.HouseRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Service
public class HouseService {
    private HouseRepository houseRepository;

    public HouseService(HouseRepository houseRepository){
        this.houseRepository = houseRepository;
    }

    public Iterable<V2House> getAllHouses() {
        return houseRepository.findAll();
    }

    public V2House getHouseById(int id) {
        return houseRepository.findById(Long.valueOf(id)).orElse(null);
    }

    public V2House saveHouse(V2House house) {
        return houseRepository.save(house);
    }

    public void deleteHouseById(int id) {
        houseRepository.deleteById(Long.valueOf(id));
    }

    public V2House updateHouse(V2House updatedHouse) {
        return houseRepository.save(updatedHouse);
    }

    public List<V2House> getAllHousesByVId(int streetNameId) {
        return houseRepository.findAll().stream().filter(x -> x.getStreetNameId().getId() == streetNameId).toList();
    }

    public List<V2House> getByName(String name){
        return houseRepository.findAll().stream().filter(x -> x.getHouseName() == name).toList();
    }
}

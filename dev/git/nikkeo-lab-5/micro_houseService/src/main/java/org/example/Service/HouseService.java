package org.example.Service;

import org.example.Converter.Converter;
import org.example.DTO.HouseDto;
import org.example.Repository.HouseRepository;
import org.example.Entities.HouseV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class HouseService {
    private final HouseRepository houseRepository;
    private final Converter converter;
    @Autowired
    public HouseService(HouseRepository houseRepository, Converter converter){
        this.houseRepository = houseRepository;
        this.converter = converter;
    }

    public List<HouseDto> getAllHouses() {
        List<HouseV2> houseV2s = houseRepository.findAll();
        List<HouseDto> houseDtos = new ArrayList<>();

        houseV2s.forEach(x -> {
            try {
                houseDtos.add(converter.convertEntityToDto(x));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        return houseDtos;
    }

    public HouseDto getHouseById(int id) {
        return converter.convertEntityToDto(houseRepository.findById(Long.valueOf(id)).orElse(null));

    }

    public HouseDto saveHouse(HouseDto house) {
        return converter.convertEntityToDto(houseRepository.save(converter.convertDtoToEntity(house)));
    }

    public void deleteHouseById(int id) {
        houseRepository.deleteById(Long.valueOf(id));
    }

    public HouseDto updateHouse(HouseDto updatedHouse) {
        return converter.convertEntityToDto(houseRepository.save(converter.convertDtoToEntity(updatedHouse)));
    }

    public List<HouseDto> getAllHousesByVId(int streetNameId) {
        List<HouseV2> houseV2s = houseRepository.findAll().stream().filter(x -> x.getStreetNameId().getId() == streetNameId).toList();
        List<HouseDto> houseDtos = new ArrayList<>();

        houseV2s.forEach(x -> {
            try {
                houseDtos.add(converter.convertEntityToDto(x));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        return houseDtos;
    }

    public List<HouseDto> getByName(String name){
        List<HouseV2> houseV2s = houseRepository.findAll().stream().filter(x -> x.getHouseName() == name).toList();
        List<HouseDto> houseDtos = new ArrayList<>();

        houseV2s.forEach(x -> {
            try {
                houseDtos.add(converter.convertEntityToDto(x));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        return houseDtos;
    }
}

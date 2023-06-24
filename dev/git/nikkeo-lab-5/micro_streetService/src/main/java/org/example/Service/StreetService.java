package org.example.Service;

import org.example.Converter.Converter;
import org.example.DTO.StreetDto;
import org.example.Repository.StreetRepository;
import org.example.Entities.StreetV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class StreetService {
    private final StreetRepository streetRepository;
    private final Converter converter;
    @Autowired
    public StreetService(StreetRepository streetRepository, Converter converter){
        this.streetRepository = streetRepository;
        this.converter = converter;
    }

    public Iterable<StreetDto> getAllStreets() {
        List<StreetV2> streetV2s = (List<StreetV2>) streetRepository.findAll();
        List<StreetDto> streetDtos = new ArrayList<>();

        streetV2s.forEach(x -> {
            try {
                streetDtos.add(converter.convertEntityToDto(x));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        return streetDtos;
    }

    public StreetDto getStreetById(int id) {
        return converter.convertEntityToDto(streetRepository.findById(Long.valueOf(id)).orElse(null));
    }

    public StreetDto saveStreet(StreetDto street) {
        streetRepository.save(converter.convertDtoToEntity(street));
        return street;
    }

    public void deleteStreetById(int id) {
        streetRepository.deleteById(Long.valueOf(id));
    }

    public Optional<StreetDto> getStreetById(Long id) {
        return Optional.ofNullable(converter.convertEntityToDto(streetRepository.findById(id).orElse(null)));
    }

    public StreetDto updateStreet(StreetDto updatedStreet) {
        streetRepository.save(converter.convertDtoToEntity(updatedStreet));
        return updatedStreet;
    }
}

package org.example.Service;

import org.example.Converter.Converter;
import org.example.DTO.ApartmentDto;
import org.example.Repository.ApartmentRepository;
import org.example.Entities.ApartmentV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApartmentService {
    private final ApartmentRepository apartmentRepository;
    private final Converter converter;
    @Autowired
    public ApartmentService(ApartmentRepository apartmentRepository, Converter converter){
        this.apartmentRepository = apartmentRepository;
        this.converter = converter;
    }
    public Iterable<ApartmentDto> getAllApartments() {
        List<ApartmentV2> apartmentV2s = apartmentRepository.findAll();
        List<ApartmentDto> apartmentDTOLIst = new ArrayList<>();

        apartmentV2s.forEach(x -> {
            try {
                apartmentDTOLIst.add(converter.convertEntityToDto(x));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        return apartmentDTOLIst;
    }

    public ApartmentDto getApartmentById(int id) throws Exception {
        if (!isFlatOwner(id))
            throw new SecurityException("Access denied");
        return converter.convertEntityToDto(apartmentRepository.findById(Long.valueOf(id)).orElse(null));
    }

    public boolean isFlatOwner(int flatId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        List<ApartmentV2> apartments = apartmentRepository.findAll();
        if (!apartments.stream().filter(x -> x.getId() == flatId).findFirst().isEmpty()){
            if (apartments.stream().filter(x -> x.getId() == flatId).findFirst().get().getUser().getUsername() == username){
                return true;
            }
        }
        return false;
    }

    public ApartmentDto saveApartment(ApartmentDto apartment) throws Exception {
        return converter.convertEntityToDto(apartmentRepository.save(converter.convertDtoToEntity(apartment)));
    }

    public void deleteApartmentById(int id) {
        apartmentRepository.deleteById(Long.valueOf(id));
    }

    public ApartmentDto updateApartment(ApartmentDto updatedApartment) throws Exception {
        return converter.convertEntityToDto(apartmentRepository.save(converter.convertDtoToEntity(updatedApartment)));
    }

    public List<ApartmentDto> getAllApartmentsByVId(int houseId) {
        List<ApartmentV2> apartmentV2s = apartmentRepository.findAll().stream().filter(x -> x.getHouseId().getId() == houseId).toList();
        List<ApartmentDto> apartmentDTOLIst = new ArrayList<>();

        apartmentV2s.forEach(x -> {
            try {
                apartmentDTOLIst.add(converter.convertEntityToDto(x));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        return apartmentDTOLIst;
    }

    public List<ApartmentDto> getByNumber(int number){
        List<ApartmentV2> apartmentV2s = apartmentRepository.findAll().stream().filter(x -> x.getNumber() == number).toList();
        List<ApartmentDto> apartmentDTOLIst = new ArrayList<>();

        apartmentV2s.forEach(x -> {
            try {
                apartmentDTOLIst.add(converter.convertEntityToDto(x));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        return apartmentDTOLIst;
    }
}

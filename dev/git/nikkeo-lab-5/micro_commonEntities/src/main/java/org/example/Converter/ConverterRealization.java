package org.example.Converter;

import org.example.DTO.ApartmentDto;
import org.example.DTO.HouseDto;
import org.example.DTO.StreetDto;
import org.example.Entities.ApartmentV2;
import org.example.Entities.HouseV2;
import org.example.Entities.StreetV2;

public class ConverterRealization implements Converter {
    public ApartmentDto convertEntityToDto(ApartmentV2 apartmentV2) throws Exception {
        if (apartmentV2 == null)
            throw new Exception("Null entity");
        ApartmentDto apartmentDTO = new ApartmentDto(apartmentV2.getId(), apartmentV2.getNumber(), apartmentV2.getArea(), apartmentV2.getNumberOfRooms());
        apartmentDTO.setHouseId(apartmentV2.getHouseId());
        apartmentDTO.setUser(apartmentV2.getUser());

        return apartmentDTO;
    }

    public ApartmentV2 convertDtoToEntity(ApartmentDto apartmentDTO) throws Exception {
        if (apartmentDTO == null)
            throw new Exception("Null entity");
        ApartmentV2 apartmentV2 = new ApartmentV2(apartmentDTO.getId(), apartmentDTO.getNumber(), apartmentDTO.getArea(), apartmentDTO.getNumberOfRooms(), apartmentDTO.getHouseId());
        apartmentV2.setUser(apartmentDTO.getUser());
        return apartmentV2;
    }

    public StreetV2 convertDtoToEntity(StreetDto streetDTO){
        StreetV2 streetV2 = new StreetV2(streetDTO.getId(), streetDTO.getStreetName(), streetDTO.getPostcode());
        streetV2.setHouses(streetDTO.getHouses());

        return streetV2;
    }

    public StreetDto convertEntityToDto(StreetV2 streetV2){
        StreetDto streetDTO = new StreetDto(streetV2.getId(), streetV2.getStreetName(), streetV2.getPostcode());
        streetDTO.setHouses(streetV2.getHouses());

        return streetDTO;
    }

    public HouseV2 convertDtoToEntity(HouseDto houseDTO){
        HouseV2 houseV2 = new HouseV2(houseDTO.getId(), houseDTO.getHouseName(), houseDTO.getDateOfConstruction(), houseDTO.getQuantityOfFloors(), houseDTO.getTypeOfConstruction(), houseDTO.getStreetNameId(), houseDTO.getMaterial());
        houseV2.setApartments(houseDTO.getApartments());

        return houseV2;
    }

    public HouseDto convertEntityToDto(HouseV2 houseV2){
        HouseDto houseDTO = new HouseDto(houseV2.getId(), houseV2.getHouseName(), houseV2.getDateOfConstruction(), houseV2.getQuantityOfFloors(), houseV2.getTypeOfConstruction(), houseV2.getStreetNameId(), houseV2.getMaterial());
        houseDTO.setApartments(houseV2.getApartments());

        return houseDTO;
    }
}

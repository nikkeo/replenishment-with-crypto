package org.example.Converter;

import org.example.DTO.ApartmentDto;
import org.example.DTO.HouseDto;
import org.example.DTO.StreetDto;
import org.example.Entities.ApartmentV2;
import org.example.Entities.HouseV2;
import org.example.Entities.StreetV2;

public interface Converter {
    public ApartmentDto convertEntityToDto(ApartmentV2 apartmentV2) throws Exception;

    public ApartmentV2 convertDtoToEntity(ApartmentDto apartmentDTO) throws Exception;

    public StreetV2 convertDtoToEntity(StreetDto streetDTO);

    public StreetDto convertEntityToDto(StreetV2 streetV2);

    public HouseV2 convertDtoToEntity(HouseDto houseDTO);

    public HouseDto convertEntityToDto(HouseV2 houseV2);
}

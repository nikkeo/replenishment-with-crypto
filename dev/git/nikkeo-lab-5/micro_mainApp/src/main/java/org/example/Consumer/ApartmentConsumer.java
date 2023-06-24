package org.example.Consumer;

import org.example.Converter.Converter;
import org.example.DTO.ApartmentDto;
import org.example.Entities.ApartmentV2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@Component
public class ApartmentConsumer {
    private RabbitTemplate template;
    private Converter converter;

    @Autowired
    public ApartmentConsumer(RabbitTemplate rabbitTemplate, Converter converter){
        this.template = rabbitTemplate;
        this.converter = converter;

    }

    public List<ApartmentDto> getAllApartments() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        List<ApartmentDto> apartmentDtos = (List<ApartmentDto>) template.convertSendAndReceive("getAllApartmentsQueue", username);

        return apartmentDtos;
    }

    public ApartmentDto getApartmentById(@PathVariable("id") int id) throws Exception {
        return (ApartmentDto) template.convertSendAndReceive("getApartmentByIdQueue", id);
    }

    public ApartmentDto saveApartment(@RequestBody ApartmentDto apartment) throws Exception {
        template.convertAndSend("saveApartmentQueue", apartment);
        return apartment;
    }

    public void deleteApartmentById(@PathVariable("id") int id) {
        template.convertAndSend("deleteOwnerQueue", id);
    }

    public ApartmentDto updateApartment(@RequestBody ApartmentDto updatedApartment) throws Exception {
        return (ApartmentDto) template.convertSendAndReceive("saveApartmentQueue", updatedApartment);
    }

    public List<ApartmentDto> getAllApartmentsByVId(@PathVariable("houseId") int houseId) {
        List<ApartmentDto> apartmentDtos = (List<ApartmentDto>) template.convertSendAndReceive("getAllApartmentsByVIdQueue", houseId);

        return apartmentDtos;
    }

    public List<ApartmentDto> getByNumber(@PathVariable("number") int number){
        List<ApartmentDto> apartmentDtos = (List<ApartmentDto>) template.convertSendAndReceive("getByNumberQueue", number);

        return apartmentDtos;
    }
}

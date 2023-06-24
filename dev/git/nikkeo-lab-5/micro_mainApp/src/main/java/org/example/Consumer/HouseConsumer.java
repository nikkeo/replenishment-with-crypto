package org.example.Consumer;

import org.example.Converter.Converter;
import org.example.DTO.HouseDto;
import org.example.Entities.HouseV2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@Component
public class HouseConsumer {
    private RabbitTemplate template;
    private Converter converter;

    @Autowired
    public HouseConsumer(RabbitTemplate rabbitTemplate, Converter converter){
        this.template = rabbitTemplate;
        this.converter = converter;
    }

    public List<HouseDto> getAllHouses() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        List<HouseDto> houseDtos = (List<HouseDto>) template.convertSendAndReceive("getAllHousesQueue", username);

        return houseDtos;
    }

    public HouseDto getHouseById(@PathVariable("id") int id) {
        return (HouseDto) template.convertSendAndReceive("getHouseByIdQueue", id);
    }

    public HouseDto saveHouse(@RequestBody HouseDto house) {
        template.convertAndSend("saveHouseQueue", house);
        return house;
    }

    public void deleteHouseById(@PathVariable("id") int id) {
        template.convertAndSend("deleteHouseByIdQueue", id);
    }

    public HouseDto updateHouse(@RequestBody HouseDto updatedHouse) {
        return (HouseDto) template.convertSendAndReceive("updateHouseQueue", updatedHouse);
    }

    public List<HouseDto> getAllHousesByVId(@PathVariable("streetNameId") int streetNameId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        List<HouseDto> houseDtos = (List<HouseDto>) template.convertSendAndReceive("getAllHousesByVIdQueue", streetNameId);

        return houseDtos;
    }

    public List<HouseDto> getByName(@PathVariable("name") String name){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        List<HouseDto> houseDtos = (List<HouseDto>) template.convertSendAndReceive("getByNameQueue", name);

        return houseDtos;
    }
}

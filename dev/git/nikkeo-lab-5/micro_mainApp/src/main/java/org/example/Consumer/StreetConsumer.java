package org.example.Consumer;

import org.example.Converter.Converter;
import org.example.DTO.StreetDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
public class StreetConsumer {
    private RabbitTemplate template;
    private Converter converter;

    @Autowired
    public StreetConsumer(RabbitTemplate rabbitTemplate, Converter converter){
        this.template = rabbitTemplate;
        this.converter = converter;
    }

    public List<StreetDto> getAllStreets() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        List<StreetDto> streetDtos = (List<StreetDto>) template.convertSendAndReceive("getAllStreetsQueue", username);

        return streetDtos;
    }

    public StreetDto getStreetById(@PathVariable("id") int id) {
        return (StreetDto) template.convertSendAndReceive("getStreetByIdQueue", id);

    }

    public StreetDto saveStreet(@RequestBody StreetDto street) {
        template.convertAndSend("saveStreetQueue", street);
        return street;
    }

    public void deleteStreetById(@PathVariable("id") int id) {
        template.convertAndSend("deleteStreetByIdQueue", id);
    }

    public StreetDto updateStreet(@RequestBody StreetDto updatedStreet) {
        template.convertAndSend("updateStreetQueue", updatedStreet);
        return updatedStreet;
    }
}

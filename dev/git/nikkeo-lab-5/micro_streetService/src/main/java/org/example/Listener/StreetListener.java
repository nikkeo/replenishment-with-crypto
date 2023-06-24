package org.example.Listener;

import org.example.DTO.StreetDto;
import org.example.Service.StreetService;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class StreetListener {
    private final StreetService streetService;
    @Autowired
    public StreetListener(StreetService streetService){
        this.streetService = streetService;
    }

    @RabbitListener(queuesToDeclare = @Queue(name = "getAllStreetsQueue"))
    public Iterable<StreetDto> getAllStreets() {
        return streetService.getAllStreets();
    }

    @RabbitListener(queuesToDeclare = @Queue(name = "getStreetByIdQueue"))
    public StreetDto getStreetById(@Payload int id) {
        return streetService.getStreetById(id);
    }

    @RabbitListener(queuesToDeclare = @Queue(name = "saveStreetQueue"))
    public StreetDto saveStreet(@Payload StreetDto streetDTO) {
        return streetService.saveStreet(streetDTO);
    }

    @RabbitListener(queuesToDeclare = @Queue(name = "deleteStreetByIdQueue"))
    public void deleteStreetById(@Payload int id) {
        streetService.deleteStreetById(id);
    }

    @RabbitListener(queuesToDeclare = @Queue(name = "updateStreetQueue"))
    public StreetDto updateStreet(@Payload StreetDto streetDTO) {
        return streetService.saveStreet(streetDTO);
    }
}

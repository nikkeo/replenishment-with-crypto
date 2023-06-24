package org.example.Listener;

import org.example.DTO.HouseDto;
import org.example.Service.HouseService;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HouseListener {
    private final HouseService houseService;
    @Autowired
    public HouseListener(HouseService houseService){
        this.houseService = houseService;
    }
    @RabbitListener(queuesToDeclare = @Queue(name = "getAllHousesQueue"))
    public List<HouseDto> getAllHouses() {
        return houseService.getAllHouses();
    }

    @RabbitListener(queuesToDeclare = @Queue(name = "getHouseByIdQueue"))
    public HouseDto getHouseById(@Payload int id) throws Exception {
        return houseService.getHouseById(id);
    }

    @RabbitListener(queuesToDeclare = @Queue(name = "saveHouseQueue"))
    public HouseDto saveHOuse(@Payload HouseDto houseDTO) throws Exception {
        return houseService.saveHouse(houseDTO);
    }

    @RabbitListener(queuesToDeclare = @Queue(name = "deleteHouseByIdQueue"))
    public void deleteHouseById(@Payload int id) {
        houseService.deleteHouseById(id);
    }

    @RabbitListener(queuesToDeclare = @Queue(name = "updateHouseQueue"))
    public HouseDto updateHouse(@Payload HouseDto updatedHouse) throws Exception {
        return houseService.saveHouse(updatedHouse);
    }

    @RabbitListener(queuesToDeclare = @Queue(name = "getAllHousesByVIdQueue"))
    public List<HouseDto> getAllHousesByVId(@Payload int houseId) {
        return houseService.getAllHousesByVId(houseId);
    }

    @RabbitListener(queuesToDeclare = @Queue(name = "getByNameQueue"))
    public List<HouseDto> getByName(@Payload String name){
        return houseService.getByName(name);
    }
}

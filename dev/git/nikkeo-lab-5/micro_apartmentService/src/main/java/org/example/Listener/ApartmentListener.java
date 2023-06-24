package org.example.Listener;

import org.example.DTO.ApartmentDto;
import org.example.Service.ApartmentService;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class ApartmentListener {
    private final ApartmentService apartmentService;
    @Autowired
    public ApartmentListener(ApartmentService apartmentService){
        this.apartmentService = apartmentService;
    }
    @RabbitListener(queuesToDeclare = @Queue(name = "getAllApartmentsQueue"))
    public Iterable<ApartmentDto> getAllApartments() {
        return apartmentService.getAllApartments();
    }

    @RabbitListener(queuesToDeclare = @Queue(name = "getApartmentByIdQueue"))
    public ApartmentDto getApartmentById(@Payload int id) throws Exception {
        return apartmentService.getApartmentById(id);
    }

    @RabbitListener(queuesToDeclare = @Queue(name = "saveApartmentQueue"))
    public ApartmentDto saveApartment(@Payload ApartmentDto apartment) throws Exception {
        return apartmentService.saveApartment(apartment);
    }

    @RabbitListener(queuesToDeclare = @Queue(name = "deleteApartmentByIdQueue"))
    public void deleteApartmentById(@Payload int id) {
        apartmentService.deleteApartmentById(id);
    }

    @RabbitListener(queuesToDeclare = @Queue(name = "updateApartmentQueue"))
    public ApartmentDto updateApartment(@Payload ApartmentDto updatedApartment) throws Exception {
        return apartmentService.saveApartment(updatedApartment);
    }

    @RabbitListener(queuesToDeclare = @Queue(name = "getAllApartmentsByVIdQueue"))
    public List<ApartmentDto> getAllApartmentsByVId(@Payload int houseId) {
        return apartmentService.getAllApartmentsByVId(houseId);
    }

    @RabbitListener(queuesToDeclare = @Queue(name = "getByNumberQueue"))
    public List<ApartmentDto> getByNumber(@Payload int number){
        return apartmentService.getByNumber(number);
    }
}

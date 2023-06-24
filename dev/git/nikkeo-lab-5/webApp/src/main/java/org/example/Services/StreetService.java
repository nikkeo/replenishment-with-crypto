package org.example.Services;

import org.example.Entities.V2Street;
import org.example.Repository.StreetRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@Service
public class StreetService {
    private StreetRepository streetRepository;

    public StreetService(StreetRepository repositories){
        this.streetRepository = repositories;
    }

    public Iterable<V2Street> getAllStreets() {
        return streetRepository.findAll();
    }

    public V2Street getStreetById(int id) {
        return streetRepository.findById(Long.valueOf(id)).orElse(null);
    }

    public V2Street saveStreet(V2Street street) {
        return streetRepository.save(street);
    }

    public void deleteStreetById(int id) {
        streetRepository.deleteById(Long.valueOf(id));
    }

    public Optional<V2Street> getStreetById(Long id) {
        return streetRepository.findById(id);
    }

    public V2Street updateStreet(V2Street updatedStreet) {
        return streetRepository.save(updatedStreet);
    }
}

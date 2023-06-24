package org.example.Repository;

import org.example.Entities.StreetV2;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StreetRepository extends CrudRepository<StreetV2, Long> {
}

package org.example.Repository;

import org.example.Entities.V2Street;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StreetRepository extends CrudRepository<V2Street, Long> {
}

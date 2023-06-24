package org.example.Repository;

import org.example.Entities.V2Apartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApartmentRepository extends JpaRepository<V2Apartment, Long> {
}

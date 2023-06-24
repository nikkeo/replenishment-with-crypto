package org.example.Repository;

import org.example.Entities.ApartmentV2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApartmentRepository extends JpaRepository<ApartmentV2, Long> {
}

package org.example.Repository;

import org.example.Entities.HouseV2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseRepository extends JpaRepository<HouseV2, Long> {
}

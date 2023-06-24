package org.example.Repository;

import org.example.Entities.V2House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseRepository extends JpaRepository<V2House, Long> {
}

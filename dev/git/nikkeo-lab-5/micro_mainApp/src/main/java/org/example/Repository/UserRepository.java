package org.example.Repository;

import org.example.Entities.UserV2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserV2, Long> {
    Optional<UserV2> findByUsername(String username);
}

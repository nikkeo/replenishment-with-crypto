package org.example.Repository;

import org.example.Entities.V2User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<V2User, Long> {
    Optional<V2User> findByUsername(String username);
}

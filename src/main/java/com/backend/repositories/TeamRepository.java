package com.backend.repositories;

import com.backend.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, String> {
    Optional<Team> findByName(String name);
}

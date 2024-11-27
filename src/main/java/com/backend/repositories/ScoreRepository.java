package com.backend.repositories;

import com.backend.entities.Score;
import com.backend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ScoreRepository extends JpaRepository<Score, String> {
    List<Score> findByUser(User user);
}

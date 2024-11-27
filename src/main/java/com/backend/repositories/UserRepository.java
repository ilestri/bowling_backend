package com.backend.repositories;

import com.backend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByStudentNumber(String studentNumber);
}

package com.backend.controllers;

import com.backend.dto.UserRequest;
import com.backend.entities.User;
import com.backend.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // 사용자 등록
    @PostMapping
    public ResponseEntity<User> registerUser(@Valid @RequestBody UserRequest userRequest) {

        // DTO를 엔티티로 변환
        User user = User.builder()
                .studentNumber(userRequest.getStudentNumber())
                .name(userRequest.getName())
                .role(userRequest.getRole())
                .build();

        User createdUser = userService.registerUser(user);
        return ResponseEntity.status(201).body(createdUser);
    }

    // 학번으로 사용자 조회
    @GetMapping("/{studentNumber}")
    public ResponseEntity<User> getUserByStudentNumber(@PathVariable String studentNumber) {
        Optional<User> userOpt = userService.getUserByStudentNumber(studentNumber);
        return userOpt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}

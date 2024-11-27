package com.backend.controllers;

import com.backend.entities.Score;
import com.backend.entities.User;
import com.backend.services.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // 모든 사용자 조회
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = adminService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // 모든 점수 조회
    @GetMapping("/scores")
    public ResponseEntity<List<Score>> getAllScores() {
        List<Score> scores = adminService.getAllScores();
        return ResponseEntity.ok(scores);
    }

    // 팀 추가, 삭제 등의 관리 기능은 TeamController에서 처리할 수 있으며,
    // 관리자 전용으로 보안 설정을 통해 접근을 제한할 수 있습니다.
}

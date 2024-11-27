package com.backend.controllers;

import com.backend.dto.ScoreRequest;
import com.backend.entities.Score;
import com.backend.entities.User;
import com.backend.services.ScoreService;
import com.backend.services.UserService;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/scores")
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private UserService userService;

    // 점수 기록
    @PostMapping
    public ResponseEntity<Score> recordScore(@Valid @RequestBody ScoreRequest scoreRequest) {
        Optional<User> userOpt = userService.getUserByStudentNumber(scoreRequest.getStudentNumber());
        if (!userOpt.isPresent()) {
            return ResponseEntity.badRequest().body(null);
        }

        Score score = Score.builder()
                .user(userOpt.get())
                .score(scoreRequest.getScore())
                .date(LocalDateTime.now())
                .build();

        Score savedScore = scoreService.recordScore(score);
        return ResponseEntity.status(201).body(savedScore);
    }

    // 특정 사용자 점수 조회
    @GetMapping("/{studentNumber}")
    public ResponseEntity<List<Score>> getScoresByUser(@PathVariable String studentNumber) {
        Optional<User> userOpt = userService.getUserByStudentNumber(studentNumber);
        if (!userOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        List<Score> scores = scoreService.getScoresByUser(userOpt.get());
        return ResponseEntity.ok(scores);
    }
}

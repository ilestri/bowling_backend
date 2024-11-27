// src/main/java/com/backend/controllers/TeamController.java
package com.backend.controllers;

import com.backend.dto.TeamAverageScore;
import com.backend.dto.TeamCreationRequest;
import com.backend.dto.TeamRanking;
import com.backend.entities.Team;
import com.backend.services.TeamService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

    @Autowired
    private TeamService teamService;

    // 팀 생성
    @PostMapping
    public ResponseEntity<Team> createTeam(@Valid @RequestBody TeamCreationRequest teamRequest) {
        try {
            Team team = teamService.createTeam(teamRequest.getName(), teamRequest.getMemberStudentNumbers());
            return ResponseEntity.status(201).body(team);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // 모든 팀 조회
    @GetMapping
    public ResponseEntity<List<Team>> getAllTeams() {
        List<Team> teams = teamService.getAllTeams();
        return ResponseEntity.ok(teams);
    }

    // 팀별 평균 점수 조회
    @GetMapping("/average-scores")
    public ResponseEntity<List<TeamAverageScore>> getTeamAverageScores() {
        List<TeamAverageScore> averageScores = teamService.getTeamAverageScores();
        return ResponseEntity.ok(averageScores);
    }

    // 팀 등수 조회
    @GetMapping("/rankings")
    public ResponseEntity<List<TeamRanking>> getTeamRankings() {
        List<TeamRanking> rankings = teamService.getTeamRankings();
        return ResponseEntity.ok(rankings);
    }
}

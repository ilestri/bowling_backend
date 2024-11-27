// src/main/java/com/backend/services/ScoreService.java
package com.backend.services;

import com.backend.dto.TeamAverageScore;
import com.backend.entities.Score;
import com.backend.entities.Team;
import com.backend.entities.User;
import com.backend.repositories.ScoreRepository;
import com.backend.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScoreService {

    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    private TeamRepository teamRepository;

    // 점수 기록
    public Score recordScore(Score score) {
        return scoreRepository.save(score);
    }

    // 특정 사용자 점수 조회
    public List<Score> getScoresByUser(User user) {
        return scoreRepository.findByUser(user);
    }

    // 모든 점수 조회 (관리자용)
    public List<Score> getAllScores() {
        return scoreRepository.findAll();
    }

    // 팀별 평균 점수 계산
    public List<TeamAverageScore> calculateTeamAverageScores() {
        List<Team> teams = teamRepository.findAll();
        List<TeamAverageScore> averageScores = new ArrayList<>();

        for (Team team : teams) {
            double average = team.getTeamMembers().stream()
                    .flatMap(member -> member.getUser().getScores().stream())
                    .mapToInt(Score::getScore)
                    .average()
                    .orElse(0.0);
            averageScores.add(new TeamAverageScore(team.getId(), team.getName(), average));
        }

        return averageScores;
    }
}

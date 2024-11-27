// src/main/java/com/backend/services/TeamService.java
package com.backend.services;

import com.backend.dto.TeamAverageScore;
import com.backend.dto.TeamRanking;
import com.backend.entities.Team;
import com.backend.entities.TeamMember;
import com.backend.entities.TeamMemberId;
import com.backend.entities.User;
import com.backend.repositories.TeamRepository;
import com.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ScoreService scoreService;

    // 팀 생성 및 멤버 지정
    @Transactional
    public Team createTeam(String teamName, List<String> memberStudentNumbers) {
        // 팀 이름 중복 확인
        if (teamRepository.findByName(teamName).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 팀 이름입니다.");
        }

        // 멤버 유효성 검사 및 사용자 조회
        List<User> members = new ArrayList<>();
        for (String studentNumber : memberStudentNumbers) {
            Optional<User> userOpt = userRepository.findByStudentNumber(studentNumber);
            if (!userOpt.isPresent()) {
                throw new IllegalArgumentException("멤버 중 존재하지 않는 사용자가 있습니다: " + studentNumber);
            }
            members.add(userOpt.get());
        }

        // 팀 생성
        Team team = Team.builder()
                .name(teamName)
                .build();
        teamRepository.save(team);

        // 팀 멤버 추가
        for (User user : members) {
            TeamMember teamMember = TeamMember.builder()
                    .id(new TeamMemberId(team.getId(), user.getId()))
                    .team(team)
                    .user(user)
                    .build();
            team.getTeamMembers().add(teamMember);
        }

        return team;
    }

    // 모든 팀 조회
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    // 팀별 평균 점수 조회
    public List<TeamAverageScore> getTeamAverageScores() {
        return scoreService.calculateTeamAverageScores();
    }

    // 팀 등수 조회
    public List<TeamRanking> getTeamRankings() {
        List<TeamAverageScore> averageScores = scoreService.calculateTeamAverageScores();
        averageScores.sort(Comparator.comparingDouble(TeamAverageScore::getAverageScore).reversed());

        List<TeamRanking> rankings = new ArrayList<>();
        int rank = 1;
        for (TeamAverageScore avg : averageScores) {
            rankings.add(new TeamRanking(rank++, avg.getTeamId(), avg.getTeamName(), avg.getAverageScore()));
        }

        return rankings;
    }
}

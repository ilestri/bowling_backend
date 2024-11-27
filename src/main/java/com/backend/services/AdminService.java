package com.backend.services;

import com.backend.entities.Score;
import com.backend.entities.Team;
import com.backend.entities.User;
import com.backend.repositories.ScoreRepository;
import com.backend.repositories.TeamRepository;
import com.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    private TeamRepository teamRepository;

    // 모든 사용자 조회
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // 모든 점수 조회
    public List<Score> getAllScores() {
        return scoreRepository.findAll();
    }

    // 팀 추가 및 삭제 기능 등 추가 가능
}

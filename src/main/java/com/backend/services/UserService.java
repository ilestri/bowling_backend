package com.backend.services;

import com.backend.entities.User;
import com.backend.repositories.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // 사용자 등록
    public User registerUser(User user) {
        // 필요한 유효성 검사 및 비즈니스 로직 추가
        return userRepository.save(user);
    }

    // 학번으로 사용자 조회
    public Optional<User> getUserByStudentNumber(String studentNumber) {
        return userRepository.findByStudentNumber(studentNumber);
    }

    // 모든 사용자 조회 (관리자용)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // 추가적인 메서드 구현 가능
}

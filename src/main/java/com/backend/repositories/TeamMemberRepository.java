package com.backend.repositories;

import com.backend.entities.TeamMember;
import com.backend.entities.TeamMemberId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamMemberRepository extends JpaRepository<TeamMember, TeamMemberId> {
    // 추가적인 쿼리가 필요할 경우 메서드를 정의
}

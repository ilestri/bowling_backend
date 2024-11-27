package com.backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TeamMemberRequest {

    @NotBlank(message = "팀 이름은 필수 입력 항목입니다.")
    private String teamName;

    @NotBlank(message = "학번은 필수 입력 항목입니다.")
    private String studentNumber;
}

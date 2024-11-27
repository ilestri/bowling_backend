package com.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class TeamCreationRequest {

    @NotBlank(message = "팀 이름은 필수 입력 항목입니다.")
    private String name;

    @NotEmpty(message = "팀 멤버는 최소 한 명 이상이어야 합니다.")
    private List<String> memberStudentNumbers;
}

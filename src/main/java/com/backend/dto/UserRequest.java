// src/main/java/com/backend/dto/UserRequest.java
package com.backend.dto;

import com.backend.entities.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserRequest {

    @NotBlank(message = "학번은 필수 입력 항목입니다.")
    private String studentNumber;

    @NotBlank(message = "이름은 필수 입력 항목입니다.")
    private String name;

    @NotNull(message = "역할은 필수 입력 항목입니다.")
    private Role role = Role.USER; // 기본값을 USER로 설정
}

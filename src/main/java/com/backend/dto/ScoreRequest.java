// src/main/java/com/backend/dto/ScoreRequest.java
package com.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ScoreRequest {

    @NotBlank(message = "학번은 필수 입력 항목입니다.")
    private String studentNumber;

    @NotNull(message = "점수는 필수 입력 항목입니다.")
    private Integer score;
}

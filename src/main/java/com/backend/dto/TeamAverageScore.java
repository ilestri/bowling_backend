// src/main/java/com/backend/dto/TeamAverageScore.java
package com.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TeamAverageScore {
    private String teamId;
    private String teamName;
    private double averageScore;
}

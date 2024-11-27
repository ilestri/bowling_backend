// src/main/java/com/backend/dto/TeamRanking.java
package com.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TeamRanking {
    private int rank;
    private String teamId;
    private String teamName;
    private double averageScore;
}

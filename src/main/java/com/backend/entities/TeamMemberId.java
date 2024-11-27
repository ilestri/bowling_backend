package com.backend.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class TeamMemberId implements Serializable {

    @Column(name = "team_id", length = 36)
    private String teamId;

    @Column(name = "user_id", length = 36)
    private String userId;
}

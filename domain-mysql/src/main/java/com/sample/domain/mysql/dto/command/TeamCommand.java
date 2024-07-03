package com.sample.domain.mysql.dto.command;

import com.sample.domain.mysql.domain.TeamEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class TeamCommand {

    @Getter
    @Setter
    @Builder
    public static class SearchTeamCondition {
        private String teamName;
    }

    public static class CreateTeam {
        private String teamName;

        public TeamEntity toEntity() {
            return TeamEntity.builder()
                    .teamName(teamName)
                    .build();
        }
    }
}

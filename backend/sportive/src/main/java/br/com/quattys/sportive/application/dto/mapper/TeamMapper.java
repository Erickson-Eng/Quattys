package br.com.quattys.sportive.application.dto.mapper;

import br.com.quattys.sportive.application.dto.request.TeamRequest;
import br.com.quattys.sportive.application.dto.response.TeamResponse;
import br.com.quattys.sportive.business.entity.Team;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {AthleteMapper.class})
public interface TeamMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "externalId", ignore = true)
    Team requestToTeam(TeamRequest teamRequest);

    TeamResponse teamToResponse(Team team);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "externalId", ignore = true)
    void updateTeamInfo(TeamRequest teamRequest, @MappingTarget Team team);
}

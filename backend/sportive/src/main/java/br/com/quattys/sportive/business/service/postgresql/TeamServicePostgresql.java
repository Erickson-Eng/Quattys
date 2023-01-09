package br.com.quattys.sportive.business.service.postgresql;

import br.com.quattys.sportive.application.dto.mapper.TeamMapper;
import br.com.quattys.sportive.application.dto.request.TeamRequest;
import br.com.quattys.sportive.application.dto.response.TeamResponse;
import br.com.quattys.sportive.application.dto.table.TeamTableResponse;
import br.com.quattys.sportive.business.entity.Team;
import br.com.quattys.sportive.business.service.TeamService;
import br.com.quattys.sportive.business.service.exception.DatabaseViolationException;
import br.com.quattys.sportive.business.service.exception.EntityNotFoundException;
import br.com.quattys.sportive.resource.repository.TeamRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class TeamServicePostgresql implements TeamService {

    private TeamRepository teamRepository;
    private TeamMapper teamMapper;

    @Override
    public TeamResponse save(TeamRequest teamRequest) {
        try{
            Team team = teamMapper.requestToTeam(teamRequest);
            team = teamRepository.save(team);
            return teamMapper.teamToResponse(team);
        } catch (DataIntegrityViolationException e){
            log.info("[TEAM-SERVICE] - Error saving information to database");
            throw new DatabaseViolationException(e.getMessage());
        }
    }

    @Override
    public TeamResponse update(Long id, TeamRequest teamRequest) {
        log.info("[TEAM-SERVICE] - VERIFYING IF THE ID TIME {} EXISTS", id);
        Team team = verifyIfTeamExist(id);
        teamMapper.updateTeamInfo(teamRequest, team);
        team = teamRepository.save(team);
        return teamMapper.teamToResponse(team);
    }

    @Override
    public TeamResponse getTeamById(Long id) {
        log.info("[TEAM-SERVICE] - VERIFYING IF THE ID TIME {} EXISTS", id);
        Team team = verifyIfTeamExist(id);
        return teamMapper.teamToResponse(team);
    }

    @Override
    public TeamTableResponse getTeamByName(String name) {
        List<Team> teamList = teamRepository.getTeamsByName(name);
        List<TeamResponse> teamResponses = getTeamResponseListFromEntityList(teamList);
        return TeamTableResponse.builder()
                .teamResponseList(teamResponses)
                .build();
    }

    @Override
    public void deleteById(Long id) {
        log.info("[TEAM-SERVICE] - VERIFYING IF THE ID TIME {} EXISTS", id);
        Team team = verifyIfTeamExist(id);
        teamRepository.delete(team);
    }

    private List<TeamResponse> getTeamResponseListFromEntityList(List<Team> teams){
        return teams.stream().map(teamMapper::teamToResponse).collect(Collectors.toList());
    }

    private Team verifyIfTeamExist(Long id){
        return teamRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Team not registered"));
    }
}

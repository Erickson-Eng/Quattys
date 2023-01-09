package br.com.quattys.sportive.business.service;

import br.com.quattys.sportive.application.dto.request.TeamRequest;
import br.com.quattys.sportive.application.dto.response.TeamResponse;
import br.com.quattys.sportive.application.dto.table.TeamTableResponse;

public interface TeamService {

    TeamResponse save (TeamRequest teamRequest);
    TeamResponse update (Long id, TeamRequest teamRequest);
    TeamResponse getTeamById(Long id);
    TeamTableResponse getTeamByName(String name);
    void deleteById(Long id);


}

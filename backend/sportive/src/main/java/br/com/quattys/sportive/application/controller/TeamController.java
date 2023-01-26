package br.com.quattys.sportive.application.controller;

import br.com.quattys.sportive.application.dto.request.TeamRequest;
import br.com.quattys.sportive.application.dto.response.TeamResponse;
import br.com.quattys.sportive.application.dto.table.TeamTableResponse;
import br.com.quattys.sportive.business.service.TeamService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/sportive/api/v1/team")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class TeamController {

    private TeamService teamService;

    @PostMapping
    public ResponseEntity<TeamResponse> createTeam(@Valid @RequestBody TeamRequest teamRequest){
        TeamResponse teamResponse = teamService.save(teamRequest);
        URI uri = URI.create(teamResponse.getExternalId());
        return ResponseEntity.created(uri).body(teamResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeamResponse> updateTeam(@Valid @RequestBody TeamRequest teamRequest, @PathVariable Long id){
        TeamResponse teamResponse = teamService.update(id, teamRequest);
        return ResponseEntity.ok().body(teamResponse);
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<TeamResponse> getTeamById(@PathVariable Long id){
        TeamResponse teamResponse = teamService.getTeamById(id);
        return ResponseEntity.ok().body(teamResponse);
    }

    @GetMapping("/get-by-name/{name}")
    public ResponseEntity<TeamTableResponse> getTeamByName(@PathVariable String name){
        TeamTableResponse teamTableResponse = teamService.getTeamByName(name);
        return ResponseEntity.ok().body(teamTableResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        teamService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

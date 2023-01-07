package br.com.quattys.sportive.application.controller;

import br.com.quattys.sportive.application.dto.request.AthleteRequest;
import br.com.quattys.sportive.application.dto.response.AthleteResponse;
import br.com.quattys.sportive.application.dto.table.AthleteTableResponse;
import br.com.quattys.sportive.business.service.AthleteService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/sportive/api/v1/athlete")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AthleteController {

    private AthleteService athleteService;

    @PostMapping
    public ResponseEntity<AthleteResponse> saveAthlete( @Valid @RequestBody AthleteRequest athleteRequest){
        AthleteResponse athleteResponse =  athleteService.save(athleteRequest);
        URI uri = URI.create(athleteResponse.getExternalId());
        return ResponseEntity.created(uri).body(athleteResponse);
    }


    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<AthleteResponse> getAthleteById(@PathVariable Long id){
        AthleteResponse athleteResponse = athleteService.getAthleteById(id);
        return ResponseEntity.ok().body(athleteResponse);

    }

    @GetMapping("/get-by-social-name/{name}")
    public ResponseEntity<AthleteTableResponse> getAthleteBySocialName(@PathVariable("name") String socialName){
        AthleteTableResponse athleteTableResponse = athleteService.getAthleteBySocialName(socialName);
        return ResponseEntity.ok().body(athleteTableResponse);
    }

    @GetMapping("/get-by-city/{city}")
    public ResponseEntity<AthleteTableResponse> getAllAthletesFromCity(@PathVariable("city") String city){
        String value = city.replace("-", " ");
        AthleteTableResponse athleteTableResponse = athleteService.getAllAthleteFromCity(value);
        return ResponseEntity.ok().body(athleteTableResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AthleteResponse> updateAthleteData(@PathVariable Long id,
                                                             @RequestBody AthleteRequest athleteRequest){
        AthleteResponse athleteResponse = athleteService.update(id, athleteRequest);
        return ResponseEntity.ok().body(athleteResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAthlete(@PathVariable Long id){
        athleteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

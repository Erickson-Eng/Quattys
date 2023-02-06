package br.com.quattys.manager.application.controller;


import br.com.quattys.manager.application.dto.request.SportRequest;
import br.com.quattys.manager.application.dto.response.SportResponse;
import br.com.quattys.manager.application.dto.table.SportTableResponse;
import br.com.quattys.manager.business.service.SportService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/manager/api/v1/sport")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SportController {

    private SportService sportService;

    @PostMapping
    public ResponseEntity<SportResponse> createSport(@Valid @RequestBody SportRequest sportRequest){
        SportResponse sport = sportService.saveSport(sportRequest);
        URI uri = URI.create(sport.getExternalId());
        return ResponseEntity.created(uri).body(sport);
    }


    @PutMapping("/{id}")
    public ResponseEntity<SportResponse> updateSportInfo(@PathVariable Long id,
                                                          @Valid @RequestBody SportRequest sportRequest){
        SportResponse sportResponse = sportService.updateSport(id, sportRequest);
        return ResponseEntity.ok().body(sportResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSport(@PathVariable Long id){
        sportService.deleteSportById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<SportResponse> getSportById(@PathVariable Long id){
        SportResponse sportResponse = sportService.findSportById(id);
        return ResponseEntity.ok().body(sportResponse);
    }

    @GetMapping("/get-by-name/{name}")
    public ResponseEntity<SportTableResponse> getSportByName(@PathVariable String name){
        SportTableResponse tableResponse = sportService.findSportByName(name);
        return ResponseEntity.ok().body(tableResponse);
    }
}

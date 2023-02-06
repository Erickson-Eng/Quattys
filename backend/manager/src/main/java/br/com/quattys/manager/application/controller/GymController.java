package br.com.quattys.manager.application.controller;

import br.com.quattys.manager.application.dto.request.GymRequest;
import br.com.quattys.manager.application.dto.response.GymResponse;
import br.com.quattys.manager.business.service.GymService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/manager/api/v1/gym")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class GymController {

    private GymService gymService;


    @PostMapping
    public ResponseEntity<GymResponse> saveGym(@Valid @RequestBody GymRequest gymRequest){
        GymResponse gymResponse = gymService.saveGym(gymRequest);
        URI uri = URI.create(gymResponse.getExternalId());
        return ResponseEntity.created(uri).body(gymResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GymResponse> updateGymInfo(@PathVariable Long id,
                                                     @Valid @RequestBody GymRequest gymRequest){
        GymResponse gymResponse = gymService.updateGym(id, gymRequest);
        return ResponseEntity.ok().body(gymResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GymResponse> deleteGym(@PathVariable Long id){
        gymService.deleteGymById(id);
        return ResponseEntity.noContent().build();
    }
}

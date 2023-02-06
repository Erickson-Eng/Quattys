package br.com.quattys.manager.application.controller;


import br.com.quattys.manager.application.dto.request.PracticableRequest;
import br.com.quattys.manager.application.dto.response.PracticableResponse;
import br.com.quattys.manager.application.dto.table.PracticableTableRequest;
import br.com.quattys.manager.application.dto.table.PracticableTableResponse;
import br.com.quattys.manager.business.service.PracticableService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/manager/api/v1/practicable")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PracticableController {


    private PracticableService practicableService;


    @PostMapping
    public ResponseEntity<PracticableTableResponse> savePracticableSports(@Valid @RequestBody PracticableTableRequest practicableTableRequest){
        PracticableTableResponse response = practicableService.savePracticableSports(practicableTableRequest);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping
    public ResponseEntity<Void> deletePracticableSports(@RequestBody List<Long> idList){
        practicableService.excludePracticableSports(idList);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PracticableResponse> updatePracticableSport(@PathVariable Long id,
                                                                      @RequestBody PracticableRequest practicableRequest){
        PracticableResponse practicableResponse = practicableService.updatePracticableInfo(id, practicableRequest);
        return ResponseEntity.ok().body(practicableResponse);
    }


}

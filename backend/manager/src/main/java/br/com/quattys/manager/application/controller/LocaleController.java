package br.com.quattys.manager.application.controller;

import br.com.quattys.manager.application.dto.request.LocaleRequest;
import br.com.quattys.manager.application.dto.response.LocaleResponse;
import br.com.quattys.manager.application.dto.table.LocaleTableResponse;
import br.com.quattys.manager.business.service.LocaleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/manager/api/v1/locale")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class LocaleController {

    private LocaleService localeService;

    @PostMapping
    public ResponseEntity<LocaleResponse> saveLocale(@Valid @RequestBody LocaleRequest localeRequest){
        LocaleResponse localeResponse = localeService.saveLocale(localeRequest);
        URI uri = URI.create(localeResponse.getExternalId());
        return ResponseEntity.created(uri).body(localeResponse);
    }


    @PutMapping("/{id}")
    public ResponseEntity<LocaleResponse> updateLocale(@PathVariable Long id, @Valid @RequestBody LocaleRequest localeRequest){
        LocaleResponse localeResponse = localeService.updateLocale(id, localeRequest);
        return ResponseEntity.ok().body(localeResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocale(@PathVariable Long id){
        localeService.deleteLocaleById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<LocaleResponse> getLocaleById(@PathVariable Long id){
        LocaleResponse localeResponse = localeService.getLocaleById(id);
        return ResponseEntity.ok().body(localeResponse);
    }

    @GetMapping("/get-by-city/{city}")
    public ResponseEntity<LocaleTableResponse> getLocaleByCity(@PathVariable String city){
        LocaleTableResponse tableResponse = localeService.getLocaleByCity(city);
        return ResponseEntity.ok().body(tableResponse);
    }
}

package br.com.quattys.backend.application.controller;

import br.com.quattys.backend.application.dto.mapper.ProfileMapper;
import br.com.quattys.backend.application.dto.request.ProfileRequest;
import br.com.quattys.backend.application.dto.response.ProfileResponse;
import br.com.quattys.backend.domain.entity.Profile;
import br.com.quattys.backend.domain.service.ProfileService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/profile")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProfileController {

    protected ProfileService profileService;
    protected ProfileMapper profileMapper;

    @PostMapping
    public ResponseEntity<ProfileResponse> createProfile(@RequestBody @Valid ProfileRequest profileRequest) {
        var profile = profileMapper.toDomain(profileRequest);
        profile = profileService.createProfile(profile);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(profile.getId())
                .toUri();
        return ResponseEntity.created(uri).body(profileMapper.toResponse(profile));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProfile(@PathVariable("id") Long id,
                                              @RequestBody @Valid ProfileRequest profileRequest) {
        var profile = profileMapper.toDomain(profileRequest);
        profileService.updateProfile(id, profile);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public Page<Profile> getProfilesByNameContainingIgnoreCase(
            @RequestParam String name,
            Pageable pageable) {
        return profileService.getProfileByNamePageable(name, pageable);
    }
}

package br.com.quattys.backend.domain.service;

import br.com.quattys.backend.domain.entity.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProfileService {

    Profile createProfile(Profile profile);

    void updateProfile(Long id, Profile profile);

    List<Profile> getProfileByName(String name);

    Page<Profile> getProfileByNamePageable(String name, Pageable pageable);
}

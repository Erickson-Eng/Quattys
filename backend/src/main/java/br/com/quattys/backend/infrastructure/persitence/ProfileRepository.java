package br.com.quattys.backend.infrastructure.persitence;

import br.com.quattys.backend.domain.entity.Profile;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

    List<Profile> findByNameContainingIgnoreCase(String name);
    Page<Profile> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
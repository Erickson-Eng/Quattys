package br.com.quattys.manager.resource.repository;

import br.com.quattys.manager.business.entity.Gym;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GymRepository extends JpaRepository<Gym, Long> {
}
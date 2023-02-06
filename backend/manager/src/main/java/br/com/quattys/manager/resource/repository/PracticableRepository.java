package br.com.quattys.manager.resource.repository;

import br.com.quattys.manager.business.entity.Practicable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PracticableRepository extends JpaRepository<Practicable, Long> {
}
package br.com.quattys.manager.resource.repository;

import br.com.quattys.manager.business.entity.Sport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SportRepository extends JpaRepository<Sport, Long> {

    List<Sport> findAllByName(String name);
}
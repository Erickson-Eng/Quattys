package br.com.quattys.sportive.resource.repository;

import br.com.quattys.sportive.business.entity.Athlete;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AthleteRepository extends JpaRepository<Athlete, Long> {
}
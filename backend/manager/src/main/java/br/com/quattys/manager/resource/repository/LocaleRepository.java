package br.com.quattys.manager.resource.repository;

import br.com.quattys.manager.business.entity.Locale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LocaleRepository extends JpaRepository<Locale, Long> {

    List<Locale> findAllByCity(String city);
}
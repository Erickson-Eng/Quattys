package br.com.quattys.backend.infrastructure.persitence;

import br.com.quattys.backend.domain.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
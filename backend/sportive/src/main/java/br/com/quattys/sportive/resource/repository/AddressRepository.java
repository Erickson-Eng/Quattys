package br.com.quattys.sportive.resource.repository;

import br.com.quattys.sportive.business.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, Long> {
    @Query(value = "select a from Address a where a.externalId = :externalId")
    Optional<Address> findAddressByExternalId(UUID externalId);
}
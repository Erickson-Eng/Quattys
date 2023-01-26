package br.com.quattys.sportive.resource.repository;

import br.com.quattys.sportive.business.entity.Membership;
import br.com.quattys.sportive.business.entity.composite_key.MembershipPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MembershipRepository extends JpaRepository<Membership, MembershipPK> {

    @Query(value = "select m from Membership m where upper(m.registrationStatus) like :status")
    List<Membership> getMembershipsByRegistrationStatus(@Param("status") String registrationStatus);


}
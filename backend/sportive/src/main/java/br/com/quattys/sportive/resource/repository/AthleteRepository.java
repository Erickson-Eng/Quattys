package br.com.quattys.sportive.resource.repository;

import br.com.quattys.sportive.business.entity.Athlete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AthleteRepository extends JpaRepository<Athlete, Long> {

    @Query(value = "select a from Athlete a where upper(a.socialName) like concat('%', upper(:socialName) , '%') ")
    List<Athlete> findAthleteBySocialName(@Param("socialName") String social);

    @Query(value = "select a from Athlete a where upper(a.address.city) like concat('%', upper(:city), '%' ) ")
    List<Athlete> findAthleteByAddress_City(@Param("city") String city);

}
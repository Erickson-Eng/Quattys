package br.com.quattys.sportive.resource.repository;

import br.com.quattys.sportive.business.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {

    @Query("select t from Team t where upper(t.name) like concat('%', upper(:name), '%' ) ")
    List<Team> getTeamsByName(@Param("name") String name);

}
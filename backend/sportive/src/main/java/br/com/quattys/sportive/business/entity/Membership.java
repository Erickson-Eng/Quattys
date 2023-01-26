package br.com.quattys.sportive.business.entity;

import br.com.quattys.sportive.business.entity.composite_key.MembershipPK;
import br.com.quattys.sportive.business.enums.RegistrationStatus;
import br.com.quattys.sportive.business.enums.TeamProfile;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "membership")
public class Membership implements Serializable {
    private static final long serialVersionUID = -7206543717068695592L;

    @EmbeddedId
    @Column(unique = true, nullable = false)
    private MembershipPK membershipPK;

    @MapsId("athleteId")
    @ManyToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "athlete_id", updatable = false, referencedColumnName = "id")
    private Athlete athlete;

    @MapsId("teamId")
    @ManyToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "team_id", updatable = false, referencedColumnName = "id")
    private Team team;

    @Enumerated(EnumType.STRING)
    private TeamProfile profile;

    @Enumerated(EnumType.STRING)
    private RegistrationStatus registrationStatus;


}
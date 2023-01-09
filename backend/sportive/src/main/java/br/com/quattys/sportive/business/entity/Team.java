package br.com.quattys.sportive.business.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "team")
public class Team implements Serializable {
    private static final long serialVersionUID = 9028558006049148154L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;
    @OneToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH})
    private Athlete owner;
    @Column(name = "external_id",
            columnDefinition = "uuid",
            unique = true, updatable = false)
    private UUID externalId;

    @OneToMany(mappedBy = "team")
    private Set<Membership> memberships;


    public Long getId() {
        return id;
    }

    @PrePersist
    public void setExternalId() {
        this.externalId = UUID.randomUUID();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Team team = (Team) o;
        return id != null && Objects.equals(id, team.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
package br.com.quattys.manager.business.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "sport")
public class Sport implements Serializable {
    private static final long serialVersionUID = -5543800138473538277L;
    private Long id;

    @Column(nullable = false)
    private String name;

    private Set<Practicable> praticableSet;

    @Column(name = "external_id",
            columnDefinition = "uuid",
            unique = true, updatable = false)
    private UUID externalId;

    @PrePersist
    public void setExternalId() {
        this.externalId = UUID.randomUUID();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    @OneToMany
    public Set<Practicable> getPraticableSet() {
        return praticableSet;
    }
}
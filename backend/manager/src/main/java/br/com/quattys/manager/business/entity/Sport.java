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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;
    @OneToMany(mappedBy = "sport")
    private Set<Practicable> praticableSet;

    @Column(name = "external_id",
            columnDefinition = "uuid",
            unique = true, updatable = false)
    private UUID externalId;

    @PrePersist
    public void setExternalId() {
        this.externalId = UUID.randomUUID();
    }


}
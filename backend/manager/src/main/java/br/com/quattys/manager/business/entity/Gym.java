package br.com.quattys.manager.business.entity;

import br.com.quattys.manager.business.enums.GymAccess;
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
@Table(name = "gym")
public class Gym implements Serializable {
    private static final long serialVersionUID = 2055072481922792042L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;

    @Column(columnDefinition = "TEXT")
    private String rules;

    @OneToOne(cascade = {CascadeType.REFRESH}, orphanRemoval = true)
    private Locale locale;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GymAccess gymAccess;

    @OneToMany(mappedBy = "gym")
    private Set<Practicable> practicableSet;

    @Column(name = "external_id",
            columnDefinition = "uuid",
            unique = true, updatable = false)
    private UUID externalId;

    @PrePersist
    public void setExternalId() {
        this.externalId = UUID.randomUUID();
    }


    public Long getId() {
        return id;
    }

}
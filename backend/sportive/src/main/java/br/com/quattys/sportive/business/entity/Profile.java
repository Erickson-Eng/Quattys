package br.com.quattys.sportive.business.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Profile implements Serializable {
    private static final long serialVersionUID = 410012683809180040L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(nullable = false)
    private String fullName;
    private String socialName;
    @Column(nullable = false)
    private LocalDate birthDate;
    @Column(length = 11)
    private String cpf;

    @Column(name = "external_id", columnDefinition = "uuid"
            , unique = true, updatable = false)
    private UUID externalId;

    @OneToOne(cascade = CascadeType.REFRESH, optional = false, orphanRemoval = true)
    @JoinColumn(nullable = false)
    private Address address;

    protected Profile(String fullName, String socialName,
                      LocalDate birthDate, String cpf, Address address) {
        this.fullName = fullName;
        this.socialName = socialName;
        this.birthDate = birthDate;
        this.cpf = cpf;
        this.address = address;
    }

    @PrePersist
    public void setExternalId() {
        this.externalId = UUID.randomUUID();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Profile profile = (Profile) o;
        return getId() != null && Objects.equals(getId(), profile.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
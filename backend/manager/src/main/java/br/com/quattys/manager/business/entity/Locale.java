package br.com.quattys.manager.business.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "locale")
public class Locale implements Serializable {
    private static final long serialVersionUID = 6285604628592634893L;
    private Long id;
    @Column(nullable = false)
    private String street;
    private String complement;
    @Column(nullable = false)
    private String number;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String state;
    @Column(nullable = false)
    private String zipCode;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Locale locale = (Locale) o;
        return getId() != null && Objects.equals(getId(), locale.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
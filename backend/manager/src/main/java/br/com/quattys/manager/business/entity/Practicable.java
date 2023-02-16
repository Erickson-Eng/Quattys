package br.com.quattys.manager.business.entity;

import br.com.quattys.manager.business.enums.AvailabilityStatus;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "practicable")
public class Practicable implements Serializable {
    private static final long serialVersionUID = -4590194911885685083L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToOne(cascade = {CascadeType.REFRESH}, optional = false)
    private Sport sport;
    @ManyToOne(cascade = {CascadeType.REFRESH}, optional = false)
    private Gym gym;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AvailabilityStatus availabilityStatus;

    @Column(name = "external_id",
            columnDefinition = "uuid",
            unique = true, updatable = false)
    private UUID externalId;

    @PrePersist
    public void setExternalId() {
        this.externalId = UUID.randomUUID();
    }


    public void setAvailabilityStatus(String availabilityStatus) {
        this.availabilityStatus = AvailabilityStatus.valueOf(availabilityStatus);
    }
}
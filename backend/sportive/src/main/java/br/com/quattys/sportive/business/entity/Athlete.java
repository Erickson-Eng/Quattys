package br.com.quattys.sportive.business.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "athlete")
public class Athlete extends Profile {
    private static final long serialVersionUID = -1523490690461201522L;

    private Double weight;
    private Double height;
    private Double bicepsMeasurement;
    private Double forearmMeasurement;
    private Double chestMeasurement;
    private Double thighMeasurement;
    private Double calfMeasurement;


    public Athlete(String fullName, String socialName, LocalDate birthDate, String cpf, Address address) {
        super(fullName, socialName, birthDate, cpf, address);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Athlete athlete = (Athlete) o;
        return getId() != null && Objects.equals(getId(), athlete.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
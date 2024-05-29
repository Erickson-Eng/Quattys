package br.com.quattys.backend.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "profile")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String name;
    private LocalDate birthday;
    private String gender;
    @Column(unique = true, nullable = false)
    private String cpf;
    @OneToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST})
    private Address homeAddress;

    @OneToOne(cascade = CascadeType.REFRESH, orphanRemoval = true)
    @JsonIgnore
    private User user;

    public Profile(Long id, String name, LocalDate birthday, String gender, String cpf, Address homeAddress) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.cpf = cpf;
        this.homeAddress = homeAddress;
    }
}
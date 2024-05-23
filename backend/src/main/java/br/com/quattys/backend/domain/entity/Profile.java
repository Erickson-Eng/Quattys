package br.com.quattys.backend.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "profile")
@AllArgsConstructor
@NoArgsConstructor
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

}
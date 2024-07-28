package br.com.amigofiel.domain.entities;

import br.com.amigofiel.domain.enums.Gender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor

public class Adoptant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToOne
    @Column(name = "address_id", nullable = false)
    private Address address;

    private Adoption[] adoptions = new Adoption[3];

}
